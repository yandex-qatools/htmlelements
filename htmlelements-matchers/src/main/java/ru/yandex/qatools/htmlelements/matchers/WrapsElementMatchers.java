package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 25.09.12
 */
public class WrapsElementMatchers {
    public static Matcher<WrapsElement> element(Matcher<WebElement> matcher) {
        return WrapsElementMatcher.element(matcher);
    }

    public static Matcher<WrapsElement> exists() {
        return element(WebElementMatchers.exists());
    }

    public static Matcher<WrapsElement> isDisplayed() {
        return element(WebElementMatchers.isDisplayed());
    }

    public static Matcher<WrapsElement> isEnabled() {
        return element(WebElementMatchers.isEnabled());
    }
}
