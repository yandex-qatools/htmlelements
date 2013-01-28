package ru.yandex.qatools.htmlelements.samples.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:22 PM
 */
public class SearchArrow extends HtmlElement {

    @FindBy(xpath = "//input[@class='b-form-input__input']")
    protected WebElement requestInput;

    @FindBy(xpath = "//input[@class='b-form-button__input']")
    protected WebElement searchButton;

    public WebElement getRequestInput() {
        return this.requestInput;
    }

    public WebElement getSearchButton() {
        return this.searchButton;
    }

    public void searchFor(String request) {
        getRequestInput().clear();
        getRequestInput().sendKeys(request);
        getSearchButton().submit();

    }
}