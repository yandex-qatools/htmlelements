package ru.yandex.qatools.htmlelements;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Form;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.03.13
 */
// TODO Refactor this test
public class FormFillingTest {
    private static final String TEXT_INPUT_NAME = "textinput";
    private static final String CHECKBOX_NAME = "checkbox";
    private static final String SELECT_NAME = "select";
    private static final String RADIO_NAME = "radio";
    private static final String TEXT_AREA_NAME = "textarea";

    private static final String INPUT_TEXT_TO_SEND = "text";
    private static final boolean CHECKBOX_VALUE_TO_SEND = true;
    private static final String SELECT_OPTION_VALUE_TO_CLICK = "value";
    private static final String RADIO_VALUE_TO_CLICK = "value";

    private WebElement formElement;
    private WebElement textInput;
    private WebElement checkBox;
    private WebElement selectOption;
    private WebElement radioButton;
    private WebElement textArea;

    private WebElement mockInputWithNameAndType(String name, String type) {
        WebElement element = mock(WebElement.class);
        when(element.getTagName()).thenReturn("input");
        when(element.getAttribute("name")).thenReturn(name);
        when(element.getAttribute("type")).thenReturn(type);
        return element;
    }

    @Before
    public void mockForm() {
        formElement = mock(WebElement.class);

        // Mock text input element
        textInput = mockInputWithNameAndType(TEXT_INPUT_NAME, "text");
        when(formElement.findElements(By.name(TEXT_INPUT_NAME))).thenReturn(Arrays.asList(textInput));

        // Mock checkbox element
        checkBox = mockInputWithNameAndType(CHECKBOX_NAME, "checkbox");
        when(formElement.findElements(By.name(CHECKBOX_NAME))).thenReturn(Arrays.asList(checkBox));
        when(checkBox.isSelected()).thenReturn(false);

        // Mock radio element
        radioButton = mockInputWithNameAndType(RADIO_NAME, "radio");
        when(formElement.findElements(By.name(RADIO_NAME))).thenReturn(Arrays.asList(radioButton));
        when(radioButton.findElements(By.xpath(String.format("self::* | following::input[@type = 'radio' " +
                "and @name = '%s'] | preceding::input[@type = 'radio' and @name = '%s']",
                RADIO_NAME, RADIO_NAME)))).thenReturn(Arrays.asList(radioButton));
        when(radioButton.getAttribute("value")).thenReturn(RADIO_VALUE_TO_CLICK);

        // Mock select element
        WebElement select = mock(WebElement.class);
        selectOption = mock(WebElement.class);
        when(select.getTagName()).thenReturn("select");
        when(select.getAttribute("name")).thenReturn(SELECT_NAME);
        when(formElement.findElements(By.name(SELECT_NAME))).thenReturn(Arrays.asList(select));
        when(select.findElements(By.xpath(String.format(".//option[@value = \"%s\"]", SELECT_OPTION_VALUE_TO_CLICK)))).
                thenReturn(Arrays.asList(selectOption));

        // Mock text area element
        textArea = mock(WebElement.class);
        when(formElement.findElements(By.name(TEXT_AREA_NAME))).thenReturn(Arrays.asList(textArea));
        when(textArea.getTagName()).thenReturn("textarea");
        when(textArea.getAttribute("name")).thenReturn(TEXT_AREA_NAME);
    }

    @Test
    public void formElementsShouldBeFilledCorrectly() {
        // Prepare data to fill
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(TEXT_INPUT_NAME, INPUT_TEXT_TO_SEND);
        data.put(CHECKBOX_NAME, CHECKBOX_VALUE_TO_SEND);
        data.put(RADIO_NAME, RADIO_VALUE_TO_CLICK);
        data.put(SELECT_NAME, SELECT_OPTION_VALUE_TO_CLICK);
        data.put(TEXT_AREA_NAME, INPUT_TEXT_TO_SEND);

        // Fill form
        Form form = new Form(formElement);
        form.fill(data);

        // Verify that form elements are filled correctly
        verify(textInput).sendKeys(INPUT_TEXT_TO_SEND);
        verify(checkBox).click();
        verify(radioButton).click();
        verify(selectOption).click();
        verify(textArea).sendKeys(INPUT_TEXT_TO_SEND);
    }
}
