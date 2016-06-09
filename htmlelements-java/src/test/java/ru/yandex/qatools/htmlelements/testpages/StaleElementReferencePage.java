package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
public class StaleElementReferencePage {

    public static final String ELEMENT_XPATH = "//div";

    public static final String ELEMENT_TEXT = "element text";

    @FindBy(xpath = ELEMENT_XPATH)
    private WebElement element;

    public StaleElementReferencePage() {
        this(mockDriver());
    }

    public StaleElementReferencePage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public WebElement getElement() {
        return element;
    }

    public static WebDriver mockDriver() {

        WebDriver driver = mock(WebDriver.class);
        WebElement element = mock(WebElement.class);

        when(element.getText())
                .thenThrow(new StaleElementReferenceException("first"))
                .thenThrow(new StaleElementReferenceException("second"))
                .thenReturn(ELEMENT_TEXT);

        when(driver.findElement(By.xpath(ELEMENT_XPATH))).thenReturn(element);
        return driver;
    }

}
