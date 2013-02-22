package ru.yandex.qatools.htmlelements.samples.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * User: eroshenkoam
 * Date: 2/7/13, 3:13 PM
 */
public class Suggest extends TypifiedElement {

    public Suggest(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private List<WebElement> getItems() {
        return getWrappedElement().findElements(By.xpath("//li"));
    }

    public void selectByIndex(int itemIndex) {
        getItems().get(itemIndex).click();
    }

    public void selectByValue(String itemValue) {
        for (WebElement item : getItems()) {
            if (itemValue.equals(item.getText())) {
                item.click();
                return;
            }
        }
        throw new NoSuchElementException();
    }
}
