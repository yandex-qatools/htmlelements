package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;

import ru.yandex.qatools.htmlelements.testpages.CachedSearchPage;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;


/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class LazyInitializationTest {
    public static final String PAGE_URL = "http://www.yandex.ru/";
    private static final String TEST_TEXT = "Test text";

    @Test
    public void testEveryAccessInitialization() {
        SearchPage searchPage = new SearchPage();

        searchPage.getSearchArrow().getRequestInput().sendKeys(TEST_TEXT);
        searchPage.getSearchArrow().getSearchButton().click();

        searchPage.getSearchArrow().getRequestInput().sendKeys(TEST_TEXT);
        searchPage.getSearchArrow().getSearchButton().click();
    }

    @Test(expected = StaleElementReferenceException.class)
    public void testCachedInitialization()  {
        CachedSearchPage cachedSearchPage = new CachedSearchPage();

        cachedSearchPage.getSearchArrow().getRequestInput().sendKeys(TEST_TEXT);
        cachedSearchPage.getSearchArrow().getSearchButton().click();

        cachedSearchPage.getSearchArrow().getRequestInput().sendKeys(TEST_TEXT);
        cachedSearchPage.getSearchArrow().getSearchButton().click();
    }
}