package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class AnnotatedSelectPage {
    public static final String SELECT_CLASS_NAME = "select-class";

    @FindBy(className = SELECT_CLASS_NAME)
    private Select select;

    public Select getSelect() {
        return select;
    }

    public AnnotatedSelectPage() {
        this(mockDriver());
    }

    public AnnotatedSelectPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement select = mock(WebElement.class);

        when(select.getTagName()).thenReturn("select");
        when(driver.findElement(By.className(SELECT_CLASS_NAME))).thenReturn(select);

        return driver;
    }
}
