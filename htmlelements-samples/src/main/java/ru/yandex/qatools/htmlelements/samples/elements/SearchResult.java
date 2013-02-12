package ru.yandex.qatools.htmlelements.samples.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:53 PM
 */
public class SearchResult extends HtmlElement {

    @FindBy(className = "title")
    protected WebElement title;

}
