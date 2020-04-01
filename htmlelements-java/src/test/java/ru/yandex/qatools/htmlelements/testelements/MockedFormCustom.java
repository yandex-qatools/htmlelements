package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.mock;

/**
 * Based on {@link MockedForm}
 */
public class MockedFormCustom extends FormCustom implements FormMockSetup {
    private final WebElement textInput;
    private final WebElement textInputWithText;
    private final WebElement textInputCustom;
    private final WebElement checkBox;
    private final WebElement selectOption;
    private final WebElement radioButton;
    private final WebElement textArea;
    private final WebElement textAreaWithText;

    public MockedFormCustom() {
        this(mock(WebElement.class));
    }

    private MockedFormCustom(WebElement wrappedElement) {
        super(wrappedElement);
        textInput = mockTextInput(TEXT_INPUT_NAME, "");
        textInputWithText = mockTextInput(TEXT_INPUT_WITH_TEXT_NAME, "a");
        textInputCustom = mockTextInputCustom(TEXT_INPUT_CUSTOM, "1");
        checkBox = mockCheckBox();
        selectOption = mockSelectOption();
        radioButton = mockRadioButton();
        textArea = mockTextArea(TEXT_AREA_NAME, "");
        textAreaWithText = mockTextArea(TEXT_AREA_WITH_TEXT_NAME, "a");
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

    public WebElement getTextInputCustomText() {
        return textInputCustom;
    }
}
