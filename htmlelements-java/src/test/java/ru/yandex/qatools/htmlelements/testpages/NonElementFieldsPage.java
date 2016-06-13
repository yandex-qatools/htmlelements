package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * @author Graham Russell graham@ham1.co.uk
 *         Date: 24.10.2015
 */
public class NonElementFieldsPage {

    // public for ease of assertion in test
    public List<List<WebElement>> rowsCache;
    public List<List<String>> rowsAsStringCache;
    public List<String> otherCache = new ArrayList<>();

    public NonElementFieldsPage() {
        this(mockDriver());
    }

    public NonElementFieldsPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public static WebDriver mockDriver() {
        return mock(WebDriver.class);
    }
}
