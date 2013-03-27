package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * The base class to be used for making classes representing typified elements (i.e web page controls such as
 * text inputs, buttons or more complex elements).
 * <p/>
 * There are several already written classes representing standard web page controls:
 * <ul>
 * <li>{@link TextInput}</li>
 * <li>{@link Button}</li>
 * <li>{@link CheckBox}</li>
 * <li>{@link Radio}</li>
 * <li>{@link Select}</li>
 * </ul>
 * <p/>
 * But you can also write your own typified elements if it's needed. For example, like this:
 * <p/>
 * <pre class="code">
 * public class Link extends TypifiedElement {
 * public Link(WebElement wrappedElement) {
 * super(wrappedElement);
 * }
 * <p/>
 * public String getReference() {
 * return getWrappedElement().getAttribute("href");
 * }
 * <p/>
 * public void click() {
 * getWrappedElement().click();
 * }
 * <p/>
 * public String getText() {
 * return getWrappedElement().getText();
 * }
 * }
 * </pre>
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public abstract class TypifiedElement implements WrapsElement, Named {
    private final WebElement wrappedElement;
    private String name;

    /**
     * Specifies wrapped {@link WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    protected TypifiedElement(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets a name of an element. This method is used by initialization mechanism and is not intended
     * to be used directly.
     *
     * @param name Name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns specified name, actually the same as {@link #getName()} method.
     *
     * @return {@code String} representing name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Is this element displayed or not? This method avoids the problem of having to parse an
     * element's "style" attribute.
     *
     * @return Whether or not the element is displayed
     */
    public boolean isDisplayed() {
        return getWrappedElement().isDisplayed();
    }

    /**
     * Is the element currently enabled or not? This will generally return true for everything but
     * disabled input elements.
     *
     * @return True if the element is enabled, false otherwise.
     */
    public boolean isEnabled() {
        return getWrappedElement().isEnabled();
    }

    /**
     * Determine whether or not this element is selected or not. This operation only applies to input
     * elements such as checkboxes, options in a select and radio buttons.
     *
     * @return True if the element is currently selected or checked, false otherwise.
     */
    public boolean isSelected() {
        return getWrappedElement().isSelected();
    }
}
