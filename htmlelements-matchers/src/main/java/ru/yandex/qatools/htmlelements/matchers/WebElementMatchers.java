package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.matchers.common.*;

/**
 * Contains useful <a href="http://code.google.com/p/hamcrest/">matchers</a> for WebElements.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public final class WebElementMatchers {

    private WebElementMatchers() {
    }

    /**
     * Creates matcher that checks if element currently exists on page.
     */
    public static Matcher<WebElement> exists() {
        return DoesElementExistMatcher.exists();
    }

    /**
     * Creates matcher that checks if element is currently displayed on page.
     */
    public static Matcher<WebElement> isDisplayed() {
        return IsElementDisplayedMatcher.isDisplayed();
    }

    /**
     * Creates matcher that checks if element is currently enabled.
     */
    public static Matcher<WebElement> isEnabled() {
        return IsElementEnabledMatcher.isEnabled();
    }

    /**
     * Creates matcher that matches element text with given matcher.
     *
     * @param matcher Matcher to match element text with.
     */
    public static Matcher<WebElement> hasText(final Matcher<String> matcher) {
        return HasTextMatcher.hasText(matcher);
    }

    /**
     * Creates matcher that checks if element text equals to the given text.
     *
     * @param text Expected text of element.
     */
    public static Matcher<WebElement> hasText(final String text) {
        return HasTextMatcher.hasText(text);
    }

    /**
     * Creates matcher that matches value of specified attribute with the given matcher.
     *
     * @param attribute    Name of matched attribute.
     * @param valueMatcher Matcher to match attribute value with.
     */
    public static Matcher<WebElement> hasAttribute(final String attribute, final Matcher<String> valueMatcher) {
        return HasAttributeMatcher.hasAttribute(attribute, valueMatcher);
    }

    /**
     * Creates matcher that checks if value of specified element attribute equals to the given value.
     *
     * @param attribute Name of matched attribute.
     * @param value     Expected attribute value.
     */
    public static Matcher<WebElement> hasAttribute(final String attribute, final String value) {
        return HasAttributeMatcher.hasAttribute(attribute, value);
    }

    /**
     * Creates matcher that matches the 'class' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match the 'class' attribute with.
     */
    public static Matcher<WebElement> hasClass(final Matcher<String> matcher) {
        return HasAttributeMatcher.hasAttribute("class", matcher);
    }

    /**
     * Creates matcher that checks if the 'class' attribute of specified element has expected value.
     *
     * @param value Expected value of the 'class' attribute.
     */
    public static Matcher<WebElement> hasClass(final String value) {
        return HasAttributeMatcher.hasAttribute("class", value);
    }

    /**
     * Creates matcher that matches the 'name' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match the 'name' attribute with.
     */
    public static Matcher<WebElement> hasName(final Matcher<String> matcher) {
        return HasAttributeMatcher.hasAttribute("name", matcher);
    }

    /**
     * Creates matcher that checks if the 'name' attribute of specified element has expected value.
     *
     * @param value Expected value of the 'name' attribute.
     */
    public static Matcher<WebElement> hasName(final String value) {
        return HasAttributeMatcher.hasAttribute("name", value);
    }

    /**
     * Creates matcher that matches the 'id' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match the 'id' attribute with.
     */
    public static Matcher<WebElement> hasId(final Matcher<String> matcher) {
        return HasAttributeMatcher.hasAttribute("id", matcher);
    }

    /**
     * Creates matcher that checks if the 'id' attribute of specified element has expected value.
     *
     * @param value Expected value of the 'id' attribute.
     */
    public static Matcher<WebElement> hasId(final String value) {
        return HasAttributeMatcher.hasAttribute("id", value);
    }

    /**
     * Creates matcher that matches the 'value' attribute of specified element with given matcher.
     *
     * @param matcher Matcher to match the 'value' attribute with.
     */
    public static Matcher<WebElement> hasValue(final Matcher<String> matcher) {
        return HasAttributeMatcher.hasAttribute("value", matcher);
    }

    /**
     * Creates matcher that checks if the 'value' attribute of specified element has expected value.
     *
     * @param value Expected value of the 'value' attribute.
     */
    public static Matcher<WebElement> hasValue(final String value) {
        return HasAttributeMatcher.hasAttribute("value", value);
    }
}