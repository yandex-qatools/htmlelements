package ru.yandex.qatools.htmlelements.samples.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.samples.elements.SearchArrow;
import ru.yandex.qatools.htmlelements.samples.elements.SearchResult;
import ru.yandex.qatools.htmlelements.samples.elements.AuthBlock;

import java.util.List;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:53 PM
 */
public class SearchPage {

    private AuthBlock authBlock;

    @FindBy(className = "b-search__table")
    private SearchArrow searchArrow;

    @FindBy(xpath = ".//div[@class='b-body-items']//li")
    private List<SearchResult> results;

    public SearchPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }

    public AuthBlock getAuthBlock() {
        return authBlock;
    }

    public SearchArrow getSearchArrow() {
        return searchArrow;
    }

    public List<SearchResult> getResults() {
        return results;
    }

}
