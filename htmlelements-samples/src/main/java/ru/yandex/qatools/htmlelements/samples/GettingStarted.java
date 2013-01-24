package ru.yandex.qatools.htmlelements.samples;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.htmlelements.samples.pages.MainPage;
import ru.yandex.qatools.htmlelements.samples.pages.SearchPage;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WaitForMatcherDecorator.withWaitFor;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:21 PM
 */
public class GettingStarted {

    public WebDriver driver = new FirefoxDriver();

    public final String REQUEST = "test";

    @Test
    public void testOutput() throws Exception {
        driver.get("http://www.yandex.ru");

        MainPage mainPage = new MainPage(driver);
        mainPage.getSearchArrow().searchFor(REQUEST);
        assertThat(driver.getTitle(), withWaitFor(containsString(REQUEST)));
    }

    @After
    public void destroyDriver() {
        driver.quit();
    }
}
