package ru.yandex.qatools.htmlelements.matchers.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Koshelev art.koshelev@gmail.com
 *         Date: 12.04.16.
 */
public class PageWithAttributedElements {
    private static final String VARIANT_A = "//elementA";
    private static final String VARIANT_B = "//elementB";
    private static final String VARIANT_C = "//elementC";
    private static final String VARIANT_D = "//elementD";

    @FindBy(xpath = VARIANT_A)
    public WebElement focused;

    @FindBy(xpath = VARIANT_B)
    public WebElement selected;

    @FindBy(xpath = VARIANT_C)
    public WebElement checked;

    @FindBy(xpath = VARIANT_D)
    public WebElement empty;

    public PageWithAttributedElements() {
        this(mockDriver());
    }

    public PageWithAttributedElements(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement focused = mock(WebElement.class);
        WebElement selected = mock(WebElement.class);
        WebElement checked = mock(WebElement.class);
        WebElement empty = mock(WebElement.class);

        when(driver.findElement(By.xpath(VARIANT_A))).thenReturn(focused);
        when(driver.findElement(By.xpath(VARIANT_B))).thenReturn(selected);
        when(driver.findElement(By.xpath(VARIANT_C))).thenReturn(checked);
        when(driver.findElement(By.xpath(VARIANT_D))).thenReturn(empty);

        when(focused.getAttribute("focused")).thenReturn("true");
        when(selected.getAttribute("selected")).thenReturn("true");
        when(checked.getAttribute("checked")).thenReturn("true");

        return driver;
    }
}
