package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class AnnotatedHtmlElementPage {
    public final static String HTML_ELEMENT_CLASS_NAME = "element";
    public final static String HTML_ELEMENT_TAG_NAME = "div";

    @FindBy(className = HTML_ELEMENT_CLASS_NAME)
    private HtmlElement element;

    public AnnotatedHtmlElementPage() {
        this(mockDriver());
    }

    public AnnotatedHtmlElementPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public HtmlElement getElement() {
        return element;
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement element = mock(WebElement.class);

        when(element.getTagName()).thenReturn(HTML_ELEMENT_TAG_NAME);
        when(driver.findElement(By.className(HTML_ELEMENT_CLASS_NAME))).thenReturn(element);

        return driver;
    }

}
