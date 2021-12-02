package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import ru.yandex.qatools.htmlelements.matchers.common.WrapsElementMatcher;

/**
 * Contains <a href="http://code.google.com/p/hamcrest/">matchers</a> that can be used with any object implementing
 * {@link WrapsElement} interface.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 25.09.12
 */
public final class WrapsElementMatchers {

    private WrapsElementMatchers() {
    }

    /**
     * Creates matcher that matches wrapped {@link WebElement} of {@link WrapsElement} with given matcher.
     *
     * @param matcher Matcher to match wrapped element with.
     */
    public static Matcher<WrapsElement> element(final Matcher<WebElement> matcher) {
        return WrapsElementMatcher.element(matcher);
    }

    /**
     * Creates matcher that checks if element currently exists on page.
     */
    public static Matcher<WrapsElement> exists() {
        return element(WebElementMatchers.exists());
    }

    /**
     * Creates matcher that checks if element is currently displayed on page.
     */
    public static Matcher<WrapsElement> isDisplayed() {
        return element(WebElementMatchers.isDisplayed());
    }

    /**
     * Creates matcher that checks if element is currently enabled.
     */
    public static Matcher<WrapsElement> isEnabled() {
        return element(WebElementMatchers.isEnabled());
    }

    /**
     * Creates matcher that matches element text with given matcher.
     *
     * @param matcher Matcher to match element text with.
     */
    public static Matcher<WrapsElement> hasText(final Matcher<String> matcher) {
        return element(WebElementMatchers.hasText(matcher));
    }

    /**
     * Creates matcher that checks if element text equals to the given text.
     *
     * @param text Expected text of element.
     */
    public static Matcher<WrapsElement> hasText(final String text) {
        return element(WebElementMatchers.hasText(text));
    }

    /**
     * Creates matcher that matches specified attribute of element with given matcher.
     *
     * @param attribute Name of matched attribute.
     * @param matcher   Matcher to match attribute with.
     */
    public static Matcher<WrapsElement> hasAttribute(final String attribute, final Matcher<String> matcher) {
        return element(WebElementMatchers.hasAttribute(attribute, matcher));
    }

    /**
     * Creates matcher that checks if value of specified element attribute equals to the given value.
     *
     * @param attribute Name of matched attribute.
     * @param value     Expected attribute value.
     */
    public static Matcher<WrapsElement> hasAttribute(final String attribute, final String value) {
        return element(WebElementMatchers.hasAttribute(attribute, value));
    }

    /**
     * Creates matcher that matches 'class' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match 'class' attribute with.
     */
    public static Matcher<WrapsElement> hasClass(final Matcher<String> matcher) {
        return element(WebElementMatchers.hasClass(matcher));
    }

    /**
     * Creates matcher that checks if 'class' attribute of specified element has expected value.
     *
     * @param value Expected value of 'class' attribute.
     */
    public static Matcher<WrapsElement> hasClass(final String value) {
        return element(WebElementMatchers.hasClass(value));
    }

    /**
     * Creates matcher that matches 'name' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match 'name' attribute with.
     */
    public static Matcher<WrapsElement> hasName(final Matcher<String> matcher) {
        return element(WebElementMatchers.hasName(matcher));
    }

    /**
     * Creates matcher that checks if 'name' attribute of specified element has expected value.
     *
     * @param value Expected value of 'name' attribute.
     */
    public static Matcher<WrapsElement> hasName(final String value) {
        return element(WebElementMatchers.hasName(value));
    }

    /**
     * Creates matcher that matches 'is' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match 'is' attribute with.
     */
    public static Matcher<WrapsElement> hasId(final Matcher<String> matcher) {
        return element(WebElementMatchers.hasId(matcher));
    }

    /**
     * Creates matcher that checks if 'id' attribute of specified element has expected value.
     *
     * @param value Expected value of 'id' attribute.
     */
    public static Matcher<WrapsElement> hasId(final String value) {
        return element(WebElementMatchers.hasId(value));
    }

    /**
     * Creates matcher that matches 'value' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match 'value' attribute with.
     */
    public static Matcher<WrapsElement> hasValue(final Matcher<String> matcher) {
        return element(WebElementMatchers.hasValue(matcher));
    }

    /**
     * Creates matcher that checks if 'value' attribute of specified element has expected value.
     *
     * @param value Expected value of 'value' attribute.
     */
    public static Matcher<WrapsElement> hasValue(final String value) {
        return element(WebElementMatchers.hasValue(value));
    }
}
