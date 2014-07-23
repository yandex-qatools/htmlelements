package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Form;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.03.13
 */
public class MockedForm extends Form {
    public static final String TEXT_INPUT_NAME = "textinput";
    public static final String CHECKBOX_NAME = "checkbox";
    public static final String SELECT_NAME = "select";
    public static final String RADIO_NAME = "radio";
    public static final String TEXT_AREA_NAME = "textarea";

    public static final String RADIO_BUTTON_VALUE = "value";
    public static final String SELECT_OPTION_VALUE = "value";

    private final WebElement textInput;
    private final WebElement checkBox;
    private final WebElement selectOption;
    private final WebElement radioButton;
    private final WebElement textArea;

    public MockedForm() {
        this(mock(WebElement.class));
    }

    private MockedForm(WebElement wrappedElement) {
        super(wrappedElement);
        textInput = mockTextInput();
        checkBox = mockCheckBox();
        selectOption = mockSelectOption();
        radioButton = mockRadioButton();
        textArea = mockTextArea();
    }

    private WebElement mockInputWithNameAndType(String name, String type) {
        WebElement element = mock(WebElement.class);
        when(element.getTagName()).thenReturn("input");
        when(element.getAttribute("name")).thenReturn(name);
        when(element.getAttribute("type")).thenReturn(type);
        when(element.getText()).thenReturn("");
        return element;
    }

    private WebElement mockTextInput() {
        WebElement textInput = mockInputWithNameAndType(TEXT_INPUT_NAME, "text");
        when(getWrappedElement().findElements(By.name(TEXT_INPUT_NAME))).thenReturn(Arrays.asList(textInput));
        return textInput;
    }

    private WebElement mockCheckBox() {
        WebElement checkBox = mockInputWithNameAndType(CHECKBOX_NAME, "checkbox");
        when(getWrappedElement().findElements(By.name(CHECKBOX_NAME))).thenReturn(Arrays.asList(checkBox));
        when(checkBox.isSelected()).thenReturn(false);
        return checkBox;
    }

    private WebElement mockRadioButton() {
        WebElement radioButton = mockInputWithNameAndType(RADIO_NAME, "radio");
        when(getWrappedElement().findElements(By.name(RADIO_NAME))).thenReturn(Arrays.asList(radioButton));
        when(radioButton.findElements(By.xpath(String.format("self::* | following::input[@type = 'radio' " +
                "and @name = '%s'] | preceding::input[@type = 'radio' and @name = '%s']",
                RADIO_NAME, RADIO_NAME)))).thenReturn(Arrays.asList(radioButton));
        when(radioButton.getAttribute("value")).thenReturn(RADIO_BUTTON_VALUE);
        return radioButton;
    }

    private WebElement mockSelectOption() {
        WebElement select = mock(WebElement.class);
        WebElement selectOption = mock(WebElement.class);
        when(select.getTagName()).thenReturn("select");
        when(select.getAttribute("name")).thenReturn(SELECT_NAME);
        when(getWrappedElement().findElements(By.name(SELECT_NAME))).thenReturn(Arrays.asList(select));
        when(select.findElements(By.xpath(String.format(".//option[@value = \"%s\"]", SELECT_OPTION_VALUE)))).
                thenReturn(Arrays.asList(selectOption));
        return selectOption;
    }

    private WebElement mockTextArea() {
        WebElement textArea = mock(WebElement.class);
        when(getWrappedElement().findElements(By.name(TEXT_AREA_NAME))).thenReturn(Arrays.asList(textArea));
        when(textArea.getTagName()).thenReturn("textarea");
        when(textArea.getAttribute("name")).thenReturn(TEXT_AREA_NAME);
        return textArea;
    }

    public WebElement getTextInput() {
        return textInput;
    }

    public WebElement getCheckBox() {
        return checkBox;
    }

    public WebElement getSelectOption() {
        return selectOption;
    }

    public WebElement getRadioButton() {
        return radioButton;
    }

    public WebElement getTextArea() {
        return textArea;
    }
}
