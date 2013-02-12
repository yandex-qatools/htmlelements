package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class CachedSearchPage {
    @CacheLookup
    private SearchArrow searchArrow;

    public CachedSearchPage() {
        this(mockDriver());
    }

    public CachedSearchPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public SearchArrow getSearchArrow() {
        return searchArrow;
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement searchArrow = mock(WebElement.class);

        WebElement searchInput = mock(WebElement.class);
        WebElement searchButton = mock(WebElement.class);

        when(driver.findElement(By.className(SearchArrowData.SEARCH_ARROW_CLASS))).thenReturn(searchArrow);
        when(searchArrow.findElement(By.className(SearchArrowData.REQUEST_INPUT_CLASS))).thenReturn(searchInput);
        when(searchArrow.findElement(By.className(SearchArrowData.SEARCH_BUTTON_CLASS))).thenReturn(searchButton);

        // cached element lost on refresh
        when(driver.findElement(By.className(SearchArrowData.SEARCH_ARROW_CLASS))).thenThrow(new StaleElementReferenceException(""));

        return driver;
    }

}