package ru.yandex.qatools.htmlelements.samples.reuseofelements.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:53 PM
 */
public class SearchResult extends HtmlElement {

    @FindBy(className = "title")
    private WebElement title;

    public WebElement getTitle() {
        return title;
    }
}
