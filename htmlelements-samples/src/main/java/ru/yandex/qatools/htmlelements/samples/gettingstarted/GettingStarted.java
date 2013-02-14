package ru.yandex.qatools.htmlelements.samples.gettingstarted;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.htmlelements.samples.gettingstarted.pages.MainPage;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:21 PM
 */
public class GettingStarted {

    public WebDriver driver = new FirefoxDriver();

    public static final String REQUEST = "test";

    @Before
    public void loadStartPage() {
        driver.get("http://www.yandex.ru");
    }

    @Test
    public void testOutput() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSearchArrow().searchFor(REQUEST);
        assertThat(driver.getTitle(), containsString(REQUEST));
    }

    @After
    public void destroyDriver() {
        driver.quit();
    }
}
