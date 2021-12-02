package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

/**
 * This matcher allows you to use {@link WebElement} matchers with objects implementing {@link WrapsElement} interface.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class WrapsElementMatcher extends TypeSafeMatcher<WrapsElement> {
    private final Matcher<WebElement> matcher;

    public WrapsElementMatcher(Matcher<WebElement> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(WrapsElement element) {
        return matcher.matches(element.getWrappedElement()) ||
                matcher.matches(element);
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WrapsElement element, Description mismatchDescription) {
        matcher.describeMismatch(element.getWrappedElement(), mismatchDescription);
    }

    /**
     * Creates matcher that matches wrapped {@link WebElement} of {@link WrapsElement} with given matcher.
     *
     * @param matcher Matcher to match wrapped element with.
     */
    @Factory
    public static Matcher<WrapsElement> element(final Matcher<WebElement> matcher) {
        return new WrapsElementMatcher(matcher);
    }
}
