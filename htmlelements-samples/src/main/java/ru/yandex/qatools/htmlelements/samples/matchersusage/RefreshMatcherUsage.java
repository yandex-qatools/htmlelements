package ru.yandex.qatools.htmlelements.samples.matchersusage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.htmlelements.samples.gettingstarted.pages.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.htmlelements.matchers.RefreshMatcherDecorator.withPrerefresh;
import static ru.yandex.qatools.htmlelements.matchers.WaitForMatcherDecorator.withWaitFor;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 12.02.13
 * Time: 0:53
 */
public class RefreshMatcherUsage {

    public static final String REQUEST = "test";
    private WebDriver driver = new FirefoxDriver();

    @Before
    public void openingThePage() {
        driver.get("http://www.yandex.ru");
    }

    @Test
    public void refreshingBeforeMatch() {
        MainPage mainPage = new MainPage(driver);
        String currentText = mainPage.getSearchSample().getText();
        assertThat(mainPage.getSearchSample(), withPrerefresh(not(hasText(currentText)), driver));
    }

    @Test
    public void howFailMessageLooksLike() {
        int timeoutInMilliseconds = 1000;

        MainPage mainPage = new MainPage(driver);
        String currentText = mainPage.getSearchSample().getText();
        assertThat(mainPage.getSearchSample(),
                withWaitFor(withPrerefresh(hasText(currentText), driver), timeoutInMilliseconds));
    }

    @After
    public void tearDownDriver() throws Exception {
        driver.quit();
    }
}
