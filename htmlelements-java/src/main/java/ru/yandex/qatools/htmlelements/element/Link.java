package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

/**
 * Represents hyperlink.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 */
public class Link extends TypifiedElement {
    public Link(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Retrieves reference from "href" attribute.
     *
     * @return Reference associated with hyperlink.
     */
    public String getReference() {
        return getWrappedElement().getAttribute("href");
    }
}
