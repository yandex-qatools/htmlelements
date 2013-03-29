package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Checks if specified {@code WebElement} currently exists on page.
 * <p/>
 * This matcher is very useful to test if some dynamically loaded page element which is represented with
 * lazily initialised {@code WebElement} has already appeared on page.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class DoesElementExistMatcher extends TypeSafeMatcher<WebElement> {
    @Override
    protected boolean matchesSafely(WebElement element) {
        try {
            element.findElement(By.xpath("self::*"));
        } catch (WebDriverException e) {
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element existing on page");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription.appendText("element ").
                appendValue(element).
                appendText(" not existing on page");
    }

    /**
     * Creates matcher that checks if element currently exists on page.
     */
    @Factory
    public static Matcher<WebElement> exists() {
        return new DoesElementExistMatcher();
    }
}
