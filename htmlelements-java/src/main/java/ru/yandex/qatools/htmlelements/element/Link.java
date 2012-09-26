package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

public class Link extends TypifiedElement {
    public Link(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public String getReference() {
        return getWrappedElement().getAttribute("href");
    }

    public void click() {
        getWrappedElement().click();
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}





