package ru.yandex.qatools.htmlelements.samples.reuseofelements;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import ru.yandex.qatools.htmlelements.samples.gettingstarted.MainPage;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * User: eroshenkoam
 * Date: 1/28/13, 5:17 PM
 */
public class ReuseOfElements {

    public WebDriver driver = new HtmlUnitDriver();

    public static final String REQUEST = "test";

    public static final String ANOTHER_REQUEST = "request";

    public static final int SEARCH_REQUEST_COUNT = 10;

    @Before
    public void loadStartPage() {
        driver.get("http://www.yandex.ru");
    }

    @Test
    public void testOutput() throws Exception {

        MainPage mainPage = new MainPage(driver);
        mainPage.getSearchArrow().searchFor(REQUEST);

        SearchPage searchPage = new SearchPage(driver);
        assertThat(searchPage.getResults(), hasSize(SEARCH_REQUEST_COUNT));

        searchPage.getSearchArrow().searchFor(ANOTHER_REQUEST);
        assertThat(searchPage.getResults(), hasSize(SEARCH_REQUEST_COUNT));
    }

    @After
    public void destroyDriver() {
        driver.quit();
    }
}
