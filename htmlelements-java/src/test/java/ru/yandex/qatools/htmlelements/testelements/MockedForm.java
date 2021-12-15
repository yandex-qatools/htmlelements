package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Form;

import static org.mockito.Mockito.mock;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.03.13
 */
public class MockedForm extends Form implements FormMockSetup {
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

    MockedForm(WebElement wrappedElement) {
        super(wrappedElement);
        textInput = mockTextInput(TEXT_INPUT_NAME, "");
        textInputWithText = mockTextInput(TEXT_INPUT_WITH_TEXT_NAME, "a");
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
}
