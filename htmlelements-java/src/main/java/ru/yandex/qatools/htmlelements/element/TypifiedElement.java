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
 *     public Link(WebElement wrappedElement) {
 *         super(wrappedElement);
 *     }
 *
 *     public String getReference() {
 *         return getWrappedElement().getAttribute("href");
 *     }
 *
 *     public void click() {
 *         getWrappedElement().click();
 *     }
 *
 *     public String getText() {
 *         return getWrappedElement().getText();
 *     }
 * }
 * </pre>
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public abstract class TypifiedElement implements WrapsElement, Named {
    private WebElement wrappedElement;
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
}
