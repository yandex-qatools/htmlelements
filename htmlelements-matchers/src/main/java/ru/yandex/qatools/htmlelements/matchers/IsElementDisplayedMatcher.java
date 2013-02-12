package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

/**
 * Indicates if element is displayed on page or not.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class IsElementDisplayedMatcher extends TypeSafeMatcher<WebElement> {
    @Override
    protected boolean matchesSafely(WebElement element) {
        return element.isDisplayed();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is displayed on page");
    }

    @Override
    public void describeMismatchSafely(WebElement element, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("element '%s' is not displayed on page", element));
    }

    /**
     * Creates matcher that checks if element is currently displayed on page.
     */
    @Factory
    public static Matcher<WebElement> isDisplayed() {
        return new IsElementDisplayedMatcher();
    }
}
