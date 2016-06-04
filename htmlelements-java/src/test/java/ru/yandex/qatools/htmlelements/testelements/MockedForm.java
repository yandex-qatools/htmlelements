package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Form;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.03.13
 */
public class MockedForm extends Form {
    public static final String TEXT_INPUT_NAME = "textinput";
    public static final String TEXT_INPUT_WITH_TEXT_NAME = "textinput_with_text";
    public static final String CHECKBOX_NAME = "checkbox";
    public static final String SELECT_NAME = "select";
    public static final String RADIO_NAME = "radio";
    public static final String TEXT_AREA_NAME = "textarea";
    public static final String TEXT_AREA_WITH_TEXT_NAME = "textarea_with_text";

    public static final String RADIO_BUTTON_VALUE = "value";
    public static final String SELECT_OPTION_VALUE = "value";

    private final WebElement textInput;
    private final WebElement textInputWithText;
    private final WebElement checkBox;
    private final WebElement selectOption;
    private final WebElement radioButton;
    private final WebElement textArea;
    private final WebElement textAreaWithText;

    public MockedForm() {
        this(mock(WebElement.class));
    }

    private MockedForm(WebElement wrappedElement) {
        super(wrappedElement);
        textInput = mockTextInput(TEXT_INPUT_NAME, "");
        textInputWithText = mockTextInput(TEXT_INPUT_WITH_TEXT_NAME, "a");
        checkBox = mockCheckBox();
        selectOption = mockSelectOption();
        radioButton = mockRadioButton();
        textArea = mockTextArea(TEXT_AREA_NAME, "");
        textAreaWithText = mockTextArea(TEXT_AREA_WITH_TEXT_NAME, "a");
    }

    private WebElement mockInputWithNameAndType(String name, String type) {
        WebElement element = mock(WebElement.class);
        when(element.getTagName()).thenReturn("input");
        when(element.getAttribute("name")).thenReturn(name);
        when(element.getAttribute("type")).thenReturn(type);
        return element;
    }

    private WebElement mockTextInput(String name, String text) {
        WebElement textInput = mockInputWithNameAndType(name, "text");
        when(getWrappedElement().findElements(By.name(name))).thenReturn(singletonList(textInput));
        when(textInput.getAttribute("value")).thenReturn(text);
        return textInput;
    }

    private WebElement mockCheckBox() {
        WebElement checkBox = mockInputWithNameAndType(CHECKBOX_NAME, "checkbox");
        when(getWrappedElement().findElements(By.name(CHECKBOX_NAME))).thenReturn(singletonList(checkBox));
        when(checkBox.isSelected()).thenReturn(false);
        return checkBox;
    }

    private WebElement mockRadioButton() {
        WebElement radioButton = mockInputWithNameAndType(RADIO_NAME, "radio");
        when(getWrappedElement().findElements(By.name(RADIO_NAME))).thenReturn(singletonList(radioButton));
        when(radioButton.findElements(By.xpath(String.format("self::* | following::input[@type = 'radio' " +
                "and @name = '%s'] | preceding::input[@type = 'radio' and @name = '%s']",
                RADIO_NAME, RADIO_NAME)))).thenReturn(singletonList(radioButton));
        when(radioButton.getAttribute("value")).thenReturn(RADIO_BUTTON_VALUE);
        return radioButton;
    }

    private WebElement mockSelectOption() {
        WebElement select = mock(WebElement.class);
        WebElement selectOption = mock(WebElement.class);
        when(select.getTagName()).thenReturn("select");
        when(select.getAttribute("name")).thenReturn(SELECT_NAME);
        when(getWrappedElement().findElements(By.name(SELECT_NAME))).thenReturn(singletonList(select));
        when(select.findElements(By.xpath(String.format(".//option[@value = \"%s\"]", SELECT_OPTION_VALUE)))).
                thenReturn(singletonList(selectOption));
        return selectOption;
    }

    private WebElement mockTextArea(String name, String text) {
        WebElement textArea = mock(WebElement.class);
        when(getWrappedElement().findElements(By.name(name))).thenReturn(singletonList(textArea));
        when(textArea.getTagName()).thenReturn("textarea");
        when(textArea.getAttribute("name")).thenReturn(name);
        when(textArea.getText()).thenReturn(text);
        return textArea;
    }

    public WebElement getTextInput() {
        return textInput;
    }

    public WebElement getTextInputWithText() {
        return textInputWithText;
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

    public WebElement getTextAreaWithText() {
        return textAreaWithText;
    }
}
