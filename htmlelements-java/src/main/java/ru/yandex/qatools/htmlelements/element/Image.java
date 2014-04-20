package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

/**
 * Represents image <img />
 *
 * @author Nikolay Mulyar proxeter@yandex-team.ru
 */
public class Image extends TypifiedElement {

    public Image(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Retrieves path to image from "src" attribute
     *
     * @return Path to the image
     */
    public String getSource() {
        return getWrappedElement().getAttribute("src");
    }

    /**
     * Retrieves alternative text from "alt" attribute
     *
     * @return alternative text for image
     */
    public String getAlt() {
        return getWrappedElement().getAttribute("alt");
    }

    /**
     * Click on image
     */
    public void click() {
        getWrappedElement().click();
    }

}
