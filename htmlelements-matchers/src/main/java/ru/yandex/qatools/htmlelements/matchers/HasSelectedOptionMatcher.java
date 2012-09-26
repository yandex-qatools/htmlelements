package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Select;

/**
 * Checks that {@link Select} has selected option matching the specified matcher.
 * In case select has not selected option this matcher will also return false.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.09.12
 */
public class HasSelectedOptionMatcher extends TypeSafeMatcher<Select> {
    private final Matcher<WebElement> matcher;

    public HasSelectedOptionMatcher(Matcher<WebElement> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Select select) {
        try {
            return matcher.matches(select.getFirstSelectedOption());
        } catch (NoSuchElementException e) {
            // Process case if no options are selected
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("select has selected option ");
    }

    @Override
    protected void describeMismatchSafely(Select select, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("select '%s' has not selected option ", select));
    }

    @Factory
    public static Matcher<Select> hasSelectedOption(Matcher<WebElement> optionMatcher) {
        return new HasSelectedOptionMatcher(optionMatcher);
    }
}
