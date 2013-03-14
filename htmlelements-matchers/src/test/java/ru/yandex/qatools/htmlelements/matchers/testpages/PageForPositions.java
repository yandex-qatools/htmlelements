package ru.yandex.qatools.htmlelements.matchers.testpages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class PageForPositions {

    public static final String HIGH = "//high";
    public static final String CENTER = "//center";
    public static final String INNER = "//inner";
    public static final String INTERCEPTOR = "//interceptor";
    public static final String LOW = "//low";

    @FindBy(xpath = HIGH)
    public HtmlElement high;

    @FindBy(xpath = CENTER)
    public HtmlElement center;

    @FindBy(xpath = INNER)
    public HtmlElement inner;

    @FindBy(xpath = INTERCEPTOR)
    public HtmlElement interceptor;

    @FindBy(xpath = LOW)
    public HtmlElement low;

    public PageForPositions() {
        HtmlElementLoader.populatePageObject(this, mockDriver());
    }

    public static WebDriver mockDriver() {
        WebDriver webDriver = mock(WebDriver.class);
        WebElement high = mock(WebElement.class);
        WebElement center = mock(WebElement.class);
        WebElement inner = mock(WebElement.class);
        WebElement interceptor = mock(WebElement.class);
        WebElement low = mock(WebElement.class);

        when(webDriver.findElement(By.xpath(HIGH))).thenReturn(high);
        when(webDriver.findElement(By.xpath(CENTER))).thenReturn(center);
        when(webDriver.findElement(By.xpath(INNER))).thenReturn(inner);
        when(webDriver.findElement(By.xpath(INTERCEPTOR))).thenReturn(interceptor);
        when(webDriver.findElement(By.xpath(LOW))).thenReturn(low);

        when(high.getLocation()).thenReturn(new Point(100, 100));
        when(high.getSize()).thenReturn(new Dimension(200,100));
        when(center.getLocation()).thenReturn(new Point(100, 300));
        when(center.getSize()).thenReturn(new Dimension(200, 300));
        when(inner.getLocation()).thenReturn(new Point(150, 350));
        when(inner.getSize()).thenReturn(new Dimension(100, 100));
        when(interceptor.getLocation()).thenReturn(new Point(275, 500));
        when(interceptor.getSize()).thenReturn(new Dimension(200, 100));
        when(low.getLocation()).thenReturn(new Point(100, 700));
        when(low.getSize()).thenReturn(new Dimension(200, 100));

        return webDriver;
    }
}
