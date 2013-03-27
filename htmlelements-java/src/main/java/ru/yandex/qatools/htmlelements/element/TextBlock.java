package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

/**
 * Represents textBlock on page.
 *
 * @author Afzali Behruz xy6er@yandex-team.ru
 */
public class TextBlock extends TypifiedElement {
    public TextBlock(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Returns text of the textBlock.
     */
    public String getText() {
        return getWrappedElement().getText();
    }
}
