package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

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
     * Creates matcher that checks if wrapped element of {@link WebElement} wrapper currently exists on page.
     */
    public static Matcher<WrapsElement> exists() {
        return element(WebElementMatchers.exists());
    }

    /**
     * Creates matcher that checks if wrapped element of {@link WebElement} wrapper is currently displayed on page.
     */
    public static Matcher<WrapsElement> isDisplayed() {
        return element(WebElementMatchers.isDisplayed());
    }

    /**
     * Creates matcher that checks if wrapped element of {@link WebElement} wrapper is currently enabled.
     */
    public static Matcher<WrapsElement> isEnabled() {
        return element(WebElementMatchers.isEnabled());
    }
}
