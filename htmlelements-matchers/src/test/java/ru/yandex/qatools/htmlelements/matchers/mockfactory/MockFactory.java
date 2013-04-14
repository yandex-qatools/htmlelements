package ru.yandex.qatools.htmlelements.matchers.mockfactory;

import org.openqa.selenium.*;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class MockFactory {
    public static WebDriver mockDriver() {
        return mock(WebDriver.class);
    }

    public static WebElement mockLocatableElement(By by, WebDriver driver) {
        WebElement element = mock(WebElement.class);
        when(driver.findElement(by)).thenReturn(element);
        return element;
    }

    public static WebElement mockNotLocatableElement(WebDriver driver) {
        WebElement element = mock(WebElement.class);
        when(driver.findElement(any(By.class))).thenThrow(new NoSuchElementException(""));
        when(element.findElement(any(By.class))).thenThrow(new StaleElementReferenceException(""));
        return element;
    }

    public static WebElement mockDisplayedElement(By by, WebDriver driver) {
        WebElement element = mockLocatableElement(by, driver);
        when(element.isDisplayed()).thenReturn(true);
        return element;
    }

    public static WebElement mockNotDisplayedElement(By by, WebDriver driver) {
        WebElement element = mockLocatableElement(by, driver);
        when(element.isDisplayed()).thenReturn(false);
        return element;
    }

    public static WebElement mockEnabledElement(By by, WebDriver driver) {
        WebElement element = mockLocatableElement(by, driver);
        when(element.isEnabled()).thenReturn(true);
        return element;
    }

    public static WebElement mockNotEnabledElement(By by, WebDriver driver) {
        WebElement element = mockLocatableElement(by, driver);
        when(element.isEnabled()).thenReturn(false);
        return element;
    }

    public static TextInput mockTextInput(String enteredText) {
        TextInput textInput = mock(TextInput.class);
        when(textInput.getText()).thenReturn(enteredText);
        return textInput;
    }

    public static Select mockSelect(String selectedOptionValue) {
        Select select = mock(Select.class);
        WebElement selectedOption = mock(WebElement.class);
        when(select.hasSelectedOption()).thenReturn(true);
        when(select.getFirstSelectedOption()).thenReturn(selectedOption);
        when(selectedOption.getAttribute("value")).thenReturn(selectedOptionValue);
        return select;
    }

    public static Radio mockRadio(String selectedButtonValue) {
        Radio radio = mock(Radio.class);
        WebElement selectedButton = mock(WebElement.class);
        when(radio.hasSelectedButton()).thenReturn(true);
        when(radio.getSelectedButton()).thenReturn(selectedButton);
        when(selectedButton.getAttribute("value")).thenReturn(selectedButtonValue);
        return radio;
    }
}
