package ru.yandex.qatools.htmlelements.testpages;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 30.06.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class SearchPage {
    public static final String URL = "http://www.yandex.ru/";

    private static final String LOGO_CLASS = "b-logo";

    private SearchArrow searchArrow;

    @Name("Logotype")
    @FindBy(className = LOGO_CLASS)
    private WebElement logo;

    public SearchPage() {
        this(mockDriver());
    }
    
    public SearchPage(CustomElementLocatorFactory elementLocatorFactory) {
    	HtmlElementLoader.populatePageObject(this, elementLocatorFactory);
    }

    public SearchPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public SearchArrow getSearchArrow() {
        return searchArrow;
    }

    public WebElement getLogo() {
        return logo;
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement logo = mock(WebElement.class);
        WebElement searchArrow = mock(WebElement.class);
        
        WebElement searchInput = mock(WebElement.class);
        WebElement searchInputReloaded = mock(WebElement.class);
        
        WebElement searchButton = mock(WebElement.class);
        WebElement searchButtonReloaded = mock(WebElement.class);
        
        WebElement item1 = mock(WebElement.class);
        WebElement item2 = mock(WebElement.class);
        ArrayList<WebElement> suggestItems = new ArrayList<WebElement>();
        suggestItems.add(item1);
        suggestItems.add(item2);

        when(driver.findElement(By.className(LOGO_CLASS))).thenReturn(logo);
        when(driver.findElement(By.className(SearchArrowData.SEARCH_ARROW_CLASS))).thenReturn(searchArrow);

        when(searchArrow.findElement(By.className(SearchArrowData.REQUEST_INPUT_CLASS))).thenReturn(searchInput);
        when(searchArrow.findElement(By.className(SearchArrowData.REQUEST_INPUT_CLASS))).thenReturn(searchInputReloaded);
        
        /** non-cached element reloads on page refresh */
        when(searchArrow.findElement(By.className(SearchArrowData.SEARCH_BUTTON_CLASS))).thenReturn(searchButton);
        when(searchArrow.findElement(By.className(SearchArrowData.SEARCH_BUTTON_CLASS))).thenReturn(searchButtonReloaded);
        
        when(searchArrow.findElements(By.className(SearchArrowData.SUGGEST_CLASS))).thenReturn(suggestItems);
        when(item1.getText()).thenReturn("yandex maps");
        when(item2.getText()).thenReturn("yandex");

        return driver;
    }

}