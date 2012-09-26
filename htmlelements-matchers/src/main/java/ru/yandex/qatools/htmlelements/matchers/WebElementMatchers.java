package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.match.AttributeMatcher;
import org.openqa.selenium.lift.match.TextMatcher;

import static org.hamcrest.Matchers.is;

/**
 * Contains useful <a href="http://code.google.com/p/hamcrest/">matchers</a> for WebElements.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class WebElementMatchers {
    /**
     * Creates matcher that checks if element currently exists on page.
     */
    public static Matcher<WebElement> exists() {
        return DoesElementExistMatcher.exists();
    }

    /**
     * Creates matcher that checks if element is currently displayed on page.
     *
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
     * Creates matcher that matches text of element with given matcher.
     *
     * @param matcher Matcher to match element text with.
     */
    public static Matcher<WebElement> hasText(Matcher<String> matcher) {
        return TextMatcher.text(matcher);
    }

    /**
     * Creates matcher that checks if element text equals to the given text.
     *
     * @param text Expected text of element.
     */
    public static Matcher<WebElement> hasText(String text) {
        return TextMatcher.text(is(text));
    }

    /**
     * Creates matcher that matches specified attribute of element with given matcher.
     *
     * @param attribute Name of attribute to be matched.
     * @param matcher   Matcher to match attribute with.
     */
    public static Matcher<WebElement> hasAttribute(String attribute, Matcher<String> matcher) {
        return AttributeMatcher.attribute(attribute, matcher);
    }

    /**
     * Creates matcher that checks if value of specified element attribute equals to the given value.
     *
     * @param attribute Name of attribute to be matched.
     * @param value     Expected attribute value.
     */
    public static Matcher<WebElement> hasAttribute(String attribute, String value) {
        return AttributeMatcher.attribute(attribute, is(value));
    }

    /**
     * Creates matcher that matches 'class' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'class' attribute with.
     */
    public static Matcher<WebElement> hasClass(Matcher<String> matcher) {
        return AttributeMatcher.attribute("class", matcher);
    }

    /**
     * Creates matcher that checks if 'class' attribute of specified element has expected value.
     * @param value Expected value if 'class' attribute.
     */
    public static Matcher<WebElement> hasClass(String value) {
        return AttributeMatcher.attribute("class", is(value));
    }

    /**
     * Creates matcher that matches 'name' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'name' attribute with.
     */
    public static Matcher<WebElement> hasName(Matcher<String> matcher) {
        return AttributeMatcher.attribute("name", matcher);
    }

    /**
     * Creates matcher that checks if 'name' attribute of specified element has expected value.
     * @param value Expected value if 'name' attribute.
     */
    public static Matcher<WebElement> hasName(String value) {
        return AttributeMatcher.attribute("name", is(value));
    }

    /**
     * Creates matcher that matches 'is' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'is' attribute with.
     */
    public static Matcher<WebElement> hasId(Matcher<String> matcher) {
        return AttributeMatcher.attribute("id", matcher);
    }

    /**
     * Creates matcher that checks if 'id' attribute of specified element has expected value.
     * @param value Expected value if 'id' attribute.
     */
    public static Matcher<WebElement> hasId(String value) {
        return AttributeMatcher.attribute("id", is(value));
    }

    /**
     * Creates matcher that matches 'value' attribute of specified element with given matcher.
     * @param matcher Matcher to match 'value' attribute with.
     */
    public static Matcher<WebElement> hasValue(Matcher<String> matcher) {
        return AttributeMatcher.attribute("value", matcher);
    }

    /**
     * Creates matcher that checks if 'value' attribute of specified element has expected value.
     * @param value Expected value if 'value' attribute.
     */
    public static Matcher<WebElement> hasValue(String value) {
        return AttributeMatcher.attribute("value", is(value));
    }
}