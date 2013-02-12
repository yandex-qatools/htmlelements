package ru.yandex.qatools.htmlelements.matchers.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class PageWithCheckBox {
    private static final String VARIANT_A = "//checkBoxA";
    private static final String VARIANT_B = "//checkBoxB";
    private static final String VARIANT_C = "//checkBoxC";

    @FindBy(xpath = VARIANT_A)
    public CheckBox variantA;

    @FindBy(xpath = VARIANT_B)
    public CheckBox variantB;

    @FindBy(xpath = VARIANT_C)
    public CheckBox allOfThis;

    public PageWithCheckBox() {
        this(mockDriver());
    }

    public PageWithCheckBox(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement variantA = mock(WebElement.class);
        WebElement variantB = mock(WebElement.class);

        when(driver.findElement(By.xpath(VARIANT_A))).thenReturn(variantA);
        when(driver.findElement(By.xpath(VARIANT_B))).thenReturn(variantB);
        when(driver.findElement(By.xpath(VARIANT_C))).thenThrow(new NoSuchElementException(VARIANT_C));
        when(variantA.isSelected()).thenReturn(true);
        when(variantB.isSelected()).thenReturn(false);

        return driver;
    }
}
