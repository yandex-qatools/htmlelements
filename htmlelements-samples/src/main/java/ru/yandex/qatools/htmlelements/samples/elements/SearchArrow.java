package ru.yandex.qatools.htmlelements.samples.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 15.02.13
 */
public class SearchArrow extends HtmlElement {

    @FindBy(xpath = ".//input[@class='b-form-input__input']")
    private TextInput requestInput;

    @FindBy(xpath = ".//input[@class='b-form-button__input']")
    private Button searchButton;

    public TextInput getRequestInput() {
        return requestInput;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void searchFor(String request) {
        getRequestInput().clear();
        getRequestInput().sendKeys(request);
        getSearchButton().click();
    }
}
