package ru.yandex.qatools.htmlelements.samples.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: eroshenkoam
 * Date: 1/28/13, 6:54 PM
 */
@Block(@FindBy(className = "b-domik"))
public class AuthBlock extends HtmlElement {

    @FindBy(className = "b-domik_popup-username")
    protected WebElement usernameInput;

    @FindBy(className = "b-domik_popup-password")
    protected WebElement passwordInput;

    @FindBy(className = "b-domik__submit")
    protected WebElement submitButton;

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void authorize(String login, String password) {
        getUsernameInput().sendKeys(login);
        getPasswordInput().sendKeys(password);
        getSubmitButton().submit();
    }
}
