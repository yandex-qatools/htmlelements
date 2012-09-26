package ru.yandex.qatools.htmlelements.matchers.mockfactory;

import org.openqa.selenium.*;
import ru.yandex.qatools.htmlelements.matchers.testelements.CustomBlock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
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
}
