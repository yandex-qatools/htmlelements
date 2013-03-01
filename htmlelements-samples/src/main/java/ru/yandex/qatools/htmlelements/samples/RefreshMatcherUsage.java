package ru.yandex.qatools.htmlelements.samples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.htmlelements.samples.pages.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;
import static ru.yandex.qatools.htmlelements.matchers.decorators.RefreshPageAction.pageRefresh;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 12.02.13
 * Time: 0:53
 */
public class RefreshMatcherUsage {
    private WebDriver driver = new FirefoxDriver();

    @Before
    public void openYandexHomepage() {
        driver.get("http://www.yandex.ru");
    }

    @Test
    public void refreshBeforeMatching() {
        MainPage mainPage = new MainPage(driver);
        String currentText = mainPage.getSearchSample().getText();

        assertThat(mainPage.getSearchSample(), should(not(hasText(currentText))).after(pageRefresh(driver)));
    }

    /**
     * This test should fail.
     */
    @Test
    public void showHowFailMessageLooks() {
        MainPage mainPage = new MainPage(driver);
        String currentText = mainPage.getSearchSample().getText();

        assertThat(mainPage.getSearchSample(), should(hasText(currentText)).after(pageRefresh(driver)));
    }

    @After
    public void tearDownDriver() throws Exception {
        driver.quit();
    }
}
