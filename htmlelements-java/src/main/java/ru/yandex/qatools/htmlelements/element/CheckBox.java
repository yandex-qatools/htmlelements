package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.utils.HtmlElementUtils;

import java.util.LinkedList;
import java.util.List;

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
     * Finds label corresponding to checkbox element
     *
     * @return {@code WebElement} representing label or {@code null} if no label has been found.
     */
    public WebElement getLabel() {
        WebElement label = null;
        String id = null;

        // Trying to find id attibute in element
        try {
            id = HtmlElementUtils.xpathLiteral(getWrappedElement().getAttribute("id"));
        } catch (NullPointerException | NoSuchElementException ex) {
            // Do nothing. Element doesn't have id attribute
        }

        if (id != null) {
            // Label with matching "for" attribute
            // Trying to find element from !ROOT! node
            String xpath = String.format("(//label[@for = %s])[1]", id);
            label = findLabel(By.xpath(xpath));
        }

        if (label == null) {
            // Label wrapped around checkbox
            label = findLabel(By.xpath("parent::label"));
        }

        if (label == null) {
            // Label right next to checkbox
            label = findLabel(By.xpath("following-sibling::label"));
        }

        return label;
    }

    /**
     * Finds label in checkbox element
     *
     * @param by path to label
     * @return {@code WebElement} representing label or {@code null} if label not found
     */
    private WebElement findLabel(By by) {
        try {
            return getWrappedElement().findElement(by);

        } catch (NoSuchElementException ex) {
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
        if (!isSelected()) {
            getWrappedElement().click();
        }
    }

    /**
     * Deselects checkbox if it is not already deselected.
     */
    public void deselect() {
        if (isSelected()) {
            getWrappedElement().click();
        }
    }

    /**
     * Selects checkbox if passed value is {@code true} and deselects otherwise.
     */
    public void set(boolean value) {
        if (value) {
            select();
        } else {
            deselect();
        }
    }
}
