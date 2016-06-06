package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

/**
 * The base class to be used for making classes representing typified elements (i.e web page controls such as
 * text inputs, buttons or more complex elements).
 * <p/>
 * There are several already written classes representing standard web page controls:
 * <ul>
 * <li>{@link Table}</li>
 * <li>{@link TextInput}</li>
 * <li>{@link CheckBox}</li>
 * <li>{@link Select}</li>
 * <li>{@link Link}</li>
 * <li>{@link Button}</li>
 * <li>{@link Radio}</li>
 * <li>{@link TextBlock}</li>
 * <li>{@link Image}</li>
 * <li>{@link Form}</li>
 * <li>{@link FileInput}</li>
 * </ul>
 * <p/>
 * But you can also write your own typified elements if needed. For example:
 * <p/>
 * <pre>
 * <code>public class Link extends TypifiedElement {
 * public Link(WebElement wrappedElement) {
 * super(wrappedElement);
 * }
 * <p/>
 * public String getReference() {
 * return getWrappedElement().getAttribute("href");
 * }
 * }
 * </code>
 * </pre>
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public abstract class TypifiedElement implements WrapsElement, Named, WebElement {
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
     * Determines whether or not this element exists on page.
     *
     * @return True if the element exists on page, false otherwise.
     */
    @SuppressWarnings("squid:S1166")
    // Sonar "Exception handlers should preserve the original exception" rule
    public boolean exists() {
        try {
            getWrappedElement().isDisplayed();
        } catch (NoSuchElementException ignored) {
            return false;
        }
        return true;
    }

    @Override
    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    @Override
    public void click() {
        getWrappedElement().click();
    }

    @Override
    public void submit() {
        getWrappedElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getWrappedElement().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        getWrappedElement().clear();
    }

    @Override
    public String getTagName() {
        return getWrappedElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getWrappedElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return getWrappedElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getWrappedElement().isEnabled();
    }

    @Override
    public String getText() {
        return getWrappedElement().getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWrappedElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getWrappedElement().findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return getWrappedElement().isDisplayed();
    }

    @Override
    public Point getLocation() {
        return getWrappedElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWrappedElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getWrappedElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getWrappedElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        return getWrappedElement().getScreenshotAs(target);
    }
}
