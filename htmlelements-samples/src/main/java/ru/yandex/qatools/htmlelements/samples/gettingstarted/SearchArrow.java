package ru.yandex.qatools.htmlelements.samples.gettingstarted;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: eroshenkoam
 * Date: 1/24/13, 4:22 PM
 */
public class SearchArrow extends HtmlElement {

    @FindBy(xpath = ".//input[@class='b-form-input__input']")
    private WebElement requestInput;

    @FindBy(xpath = ".//input[@class='b-form-button__input']")
    private WebElement searchButton;

    public WebElement getRequestInput() {
        return requestInput;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public void searchFor(String request) {
        getRequestInput().clear();
        getRequestInput().sendKeys(request);
        getSearchButton().submit();
    }
}