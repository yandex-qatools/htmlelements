package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public interface FormMockSetup extends WrapsElement {
    String TEXT_INPUT_NAME = "textinput";
    String TEXT_INPUT_WITH_TEXT_NAME = "textinput_with_text";
    String TEXT_INPUT_CUSTOM = "custom-input";
    String CHECKBOX_NAME = "checkbox";
    String SELECT_NAME = "select";
    String RADIO_NAME = "radio";
    String TEXT_AREA_NAME = "textarea";
    String TEXT_AREA_WITH_TEXT_NAME = "textarea_with_text";

    String RADIO_BUTTON_VALUE = "value";
    String SELECT_OPTION_VALUE = "value";

    default WebElement mockInputWithNameAndType(String name, String type) {
        WebElement element = mock(WebElement.class);
        when(element.getTagName()).thenReturn("input");
        when(element.getAttribute("name")).thenReturn(name);
        when(element.getAttribute("type")).thenReturn(type);
        return element;
    }

    default WebElement mockTextInput(String name, String text) {
        WebElement textInput = mockInputWithNameAndType(name, "text");
        when(getWrappedElement().findElements(By.name(name))).thenReturn(singletonList(textInput));
        when(textInput.getAttribute("value")).thenReturn(text);
        return textInput;
    }

    default WebElement mockTextInputCustom(String name, String text) {
        WebElement customBlockInForm = mockInputWithNameAndType(name, TextInputCustom.TYPE_ATTRIBUTE_VALUE);
        when(getWrappedElement().findElements(By.name(name))).thenReturn(singletonList(customBlockInForm));
        when(customBlockInForm.getAttribute("value")).thenReturn(text);
        return customBlockInForm;
    }

    default WebElement mockCheckBox() {
        WebElement checkBox = mockInputWithNameAndType(CHECKBOX_NAME, "checkbox");
        when(getWrappedElement().findElements(By.name(CHECKBOX_NAME))).thenReturn(singletonList(checkBox));
        when(checkBox.isSelected()).thenReturn(false);
        return checkBox;
    }

    default WebElement mockRadioButton() {
        WebElement radioButton = mockInputWithNameAndType(RADIO_NAME, "radio");
        when(getWrappedElement().findElements(By.name(RADIO_NAME))).thenReturn(singletonList(radioButton));
        when(radioButton.findElements(By.xpath(String.format("self::* | following::input[@type = 'radio' " +
                        "and @name = '%s'] | preceding::input[@type = 'radio' and @name = '%s']",
                RADIO_NAME, RADIO_NAME)))).thenReturn(singletonList(radioButton));
        when(radioButton.getAttribute("value")).thenReturn(RADIO_BUTTON_VALUE);
        return radioButton;
    }

    default WebElement mockSelectOption() {
        WebElement select = mock(WebElement.class);
        WebElement selectOption = mock(WebElement.class);
        when(select.getTagName()).thenReturn("select");
        when(select.getAttribute("name")).thenReturn(SELECT_NAME);
        when(getWrappedElement().findElements(By.name(SELECT_NAME))).thenReturn(singletonList(select));
        when(select.findElements(By.xpath(String.format(".//option[@value = \"%s\"]", SELECT_OPTION_VALUE)))).
                thenReturn(singletonList(selectOption));
        return selectOption;
    }

    default WebElement mockTextArea(String name, String text) {
        WebElement textArea = mock(WebElement.class);
        when(getWrappedElement().findElements(By.name(name))).thenReturn(singletonList(textArea));
        when(textArea.getTagName()).thenReturn("textarea");
        when(textArea.getAttribute("name")).thenReturn(name);
        when(textArea.getText()).thenReturn(text);
        return textArea;
    }
}
