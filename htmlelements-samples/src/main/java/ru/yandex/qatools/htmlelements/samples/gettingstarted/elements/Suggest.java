package ru.yandex.qatools.htmlelements.samples.gettingstarted.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * User: eroshenkoam
 * Date: 2/7/13, 3:13 PM
 */
public class Suggest extends HtmlElement {

    @FindBy(tagName = "li")
    private List<WebElement> suggestTips;

    public List<WebElement> getTips() {
        return suggestTips;
    }
}
