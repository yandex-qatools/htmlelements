package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.match.AttributeMatcher;
import org.openqa.selenium.lift.match.TextMatcher;

import static org.hamcrest.Matchers.is;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class WebElementMatchers {
    public static Matcher<WebElement> exists() {
        return DoesElementExistMatcher.exists();
    }

    public static Matcher<WebElement> isDisplayed() {
        return IsElementDisplayedMatcher.isDisplayed();
    }

    public static Matcher<WebElement> isEnabled() {
        return IsElementEnabledMatcher.isEnabled();
    }

    public static Matcher<WebElement> hasText(Matcher<String> textMatcher) {
        return TextMatcher.text(textMatcher);
    }

    public static Matcher<WebElement> hasText(String text) {
        return TextMatcher.text(is(text));
    }

    public static Matcher<WebElement> hasAttribute(String attribute, Matcher<String> matcher) {
        return AttributeMatcher.attribute(attribute, matcher);
    }

    public static Matcher<WebElement> hasAttribute(String attribute, String value) {
        return AttributeMatcher.attribute(attribute, is(value));
    }
}