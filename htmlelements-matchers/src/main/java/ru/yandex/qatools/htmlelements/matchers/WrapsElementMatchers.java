package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.lift.match.AttributeMatcher;
import org.openqa.selenium.lift.match.TextMatcher;

import static org.hamcrest.Matchers.is;

/**
 * Contains <a href="http://code.google.com/p/hamcrest/">matchers</a> that can be used with any object implementing
 * {@link WrapsElement} interface.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 25.09.12
 */
public class WrapsElementMatchers {
    /**
     * Creates matcher that matches wrapped {@link WebElement} of {@link WrapsElement} with given matcher.
     * @param matcher Matcher to match wrapped element with.
     */
    public static Matcher<WrapsElement> element(Matcher<WebElement> matcher) {
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
     * Creates matcher that matches text of element with given matcher.
     *
     * @param matcher Matcher to match element text with.
     */
    public static Matcher<WrapsElement> hasText(Matcher<String> matcher) {
        return element(TextMatcher.text(matcher));
    }

    /**
     * Creates matcher that checks if element text equals to the given text.
     *
     * @param text Expected text of element.
     */
    public static Matcher<WrapsElement> hasText(String text) {
        return element(TextMatcher.text(is(text)));
    }

    /**
     * Creates matcher that matches specified attribute of element with given matcher.
     *
     * @param attribute Name of attribute to be matched.
     * @param matcher   Matcher to match attribute with.
     */
    public static Matcher<WrapsElement> hasAttribute(String attribute, Matcher<String> matcher) {
        return element(AttributeMatcher.attribute(attribute, matcher));
    }

    /**
     * Creates matcher that checks if value of specified element attribute equals to the given value.
     *
     * @param attribute Name of attribute to be matched.
     * @param value     Expected attribute value.
     */
    public static Matcher<WrapsElement> hasAttribute(String attribute, String value) {
        return element(AttributeMatcher.attribute(attribute, is(value)));
    }

    /**
     * Creates matcher that matches 'class' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'class' attribute with.
     */
    public static Matcher<WrapsElement> hasClass(Matcher<String> matcher) {
        return element(AttributeMatcher.attribute("class", matcher));
    }

    /**
     * Creates matcher that checks if 'class' attribute of specified element has expected value.
     * @param value Expected value if 'class' attribute.
     */
    public static Matcher<WrapsElement> hasClass(String value) {
        return element(AttributeMatcher.attribute("class", is(value)));
    }

    /**
     * Creates matcher that matches 'name' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'name' attribute with.
     */
    public static Matcher<WrapsElement> hasName(Matcher<String> matcher) {
        return element(AttributeMatcher.attribute("name", matcher));
    }

    /**
     * Creates matcher that checks if 'name' attribute of specified element has expected value.
     * @param value Expected value if 'name' attribute.
     */
    public static Matcher<WrapsElement> hasName(String value) {
        return element(AttributeMatcher.attribute("name", is(value)));
    }

    /**
     * Creates matcher that matches 'is' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'is' attribute with.
     */
    public static Matcher<WrapsElement> hasId(Matcher<String> matcher) {
        return element(AttributeMatcher.attribute("id", matcher));
    }

    /**
     * Creates matcher that checks if 'id' attribute of specified element has expected value.
     * @param value Expected value if 'id' attribute.
     */
    public static Matcher<WrapsElement> hasId(String value) {
        return element(AttributeMatcher.attribute("id", is(value)));
    }

    /**
     * Creates matcher that matches 'value' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'value' attribute with.
     */
    public static Matcher<WrapsElement> hasValue(Matcher<String> matcher) {
        return element(AttributeMatcher.attribute("value", matcher));
    }

    /**
     * Creates matcher that checks if 'value' attribute of specified element has expected value.
     * @param value Expected value if 'value' attribute.
     */
    public static Matcher<WrapsElement> hasValue(String value) {
        return element(AttributeMatcher.attribute("value", is(value)));
    }
}
