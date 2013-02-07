package ru.yandex.qatools.htmlelements.samples.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.samples.elements.SearchArrow;
import ru.yandex.qatools.htmlelements.samples.elements.Suggest;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:27 PM
 */
public class MainPage {

    @FindBy(className = "b-morda-search-form")
    private SearchArrow searchArrow;

    @FindBy(xpath = "//div[@class='i-popup__content']//ul")
    private Suggest suggest;

    public MainPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }

    public SearchArrow getSearchArrow() {
        return searchArrow;
    }

    public Suggest getSuggest() {
        return suggest;
    }

}
