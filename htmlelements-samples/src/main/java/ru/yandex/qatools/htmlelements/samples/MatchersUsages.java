package ru.yandex.qatools.htmlelements.samples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.htmlelements.samples.pages.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WrapsElementMatchers.exists;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;
import static ru.yandex.qatools.htmlelements.matchers.decorators.TimeoutWaiter.timeoutHasExpired;

/**
 * User: eroshenkoam
 * Date: 2/7/13, 3:07 PM
 */
public class MatchersUsages {

    public WebDriver driver = new FirefoxDriver();

    public static final String REQUEST = "test";

    @Before
    public void loadStartPage() {
        driver.get("http://www.yandex.ru");
    }

    @Test
    public void timeoutWaiterUsage() throws Exception {
        MainPage mainPage = new MainPage(driver);
        mainPage.getSearchArrow().getRequestInput().sendKeys(REQUEST);

        assertThat(mainPage.getSuggest(), should(exists()).whileWaitingUntil(timeoutHasExpired()));
    }

    @After
    public void destroyDriver() {
        driver.quit();
    }
}
