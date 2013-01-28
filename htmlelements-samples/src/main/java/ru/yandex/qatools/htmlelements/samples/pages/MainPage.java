package ru.yandex.qatools.htmlelements.samples.pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.samples.elements.SearchArrow;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:27 PM
 */
public class MainPage {

    protected SearchArrow searchArrow;

    public MainPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }

    public SearchArrow getSearchArrow() {
        return searchArrow;
    }

}
