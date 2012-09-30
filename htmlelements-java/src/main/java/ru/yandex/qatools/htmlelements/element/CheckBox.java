package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Represents web page checkbox control.
 *
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 * @author Alexander Tolmachev starlight@yandex-team.ru
 */
public class CheckBox extends TypifiedElement {
    /**
     * Specifies wrapped {@link WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public CheckBox(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Finds label corresponding to this checkbox using "following-sibling::label" xpath.
     *
     * @return {@code WebElement} representing label or {@code null} if no label has been found.
     */
    public WebElement getLabel() {
        try {
            return getWrappedElement().findElement(By.xpath("following-sibling::label"));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Finds a text of the checkbox label.
     *
     * @return Label text or {@code null} if no label has been found.
     */
    public String getLabelText() {
        WebElement label = getLabel();
        return label == null ? null : label.getText();
    }

    /**
     * The same as {@link #getLabelText()}.
     *
     * @return Text of the checkbox label or {@code null} if no label has been found.
     */
    public String getText() {
        return getLabelText();
    }

    /**
     * Selects checkbox if it is not already selected.
     */
    public void select() {
        if (!getWrappedElement().isSelected()) {
            getWrappedElement().click();
        }
    }

    /**
     * Deselects checkbox if it is not already deselected.
     */
    public void deselect() {
        if (getWrappedElement().isSelected()) {
            getWrappedElement().click();
        }
    }

    /**
     * Indicates whether checkbox is selected.
     */
    public boolean isSelected() {
        return getWrappedElement().isSelected();
    }
}
