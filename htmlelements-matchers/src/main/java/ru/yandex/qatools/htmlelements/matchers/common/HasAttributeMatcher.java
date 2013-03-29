package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

/**
 * Matcher for matching attribute values of {@link WebElement}s with specified matcher.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 29.03.13
 */
public class HasAttributeMatcher extends TypeSafeMatcher<WebElement> {
    private final String attributeName;
    private final Matcher<String> attributeValueMatcher;

    public HasAttributeMatcher(String attributeName, Matcher<String> attributeValueMatcher) {
        this.attributeName = attributeName;
        this.attributeValueMatcher = attributeValueMatcher;
    }

    @Override
    public boolean matchesSafely(WebElement item) {
        return attributeValueMatcher.matches(item.getAttribute(attributeName));
    }

    public void describeTo(Description description) {
        description.appendText("attribute ").
                appendValue(attributeName).
                appendText(" ").
                appendDescriptionOf(attributeValueMatcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("attribute ").
                appendValue(attributeName).
                appendText(" of element ").
                appendValue(item).
                appendText(" not ").
                appendDescriptionOf(attributeValueMatcher);
    }

    @Factory
    public static Matcher<WebElement> hasAttribute(final String attributeName,
                                                   final Matcher<String> attributeValueMatcher) {
        return new HasAttributeMatcher(attributeName, attributeValueMatcher);
    }

    @Factory
    public static Matcher<WebElement> hasAttribute(final String attributeName, final String attributeValue) {
        return new HasAttributeMatcher(attributeName, is(attributeValue));
    }
}
