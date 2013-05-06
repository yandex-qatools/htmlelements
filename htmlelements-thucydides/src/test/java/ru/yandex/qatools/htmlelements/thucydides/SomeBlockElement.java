package ru.yandex.qatools.htmlelements.thucydides;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 6:12 PM
 */
public class SomeBlockElement extends HtmlElement {

    @FindBy(className = "element")
    public WebElement innerElement;
}
