package ru.yandex.qatools.htmlelements.matchers.common;

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
    private final Matcher<WebElement> optionMatcher;

    public HasSelectedOptionMatcher(Matcher<WebElement> optionMatcher) {
        this.optionMatcher = optionMatcher;
    }

    @Override
    protected boolean matchesSafely(Select select) {
        try {
            return optionMatcher.matches(select.getFirstSelectedOption());
        } catch (NoSuchElementException e) {
            // Process case if no options are selected
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("selected option ").appendDescriptionOf(optionMatcher);
    }

    @Override
    protected void describeMismatchSafely(Select select, Description mismatchDescription) {
        mismatchDescription.appendValue(select).
                appendText("selected option not ").
                appendDescriptionOf(optionMatcher);
    }

    /**
     * Creates matcher that tests if {@link Select} has selected option matching the specified matcher.
     *
     * @param optionMatcher Matcher to match selected option with.
     */
    @Factory
    public static Matcher<Select> hasSelectedOption(final Matcher<WebElement> optionMatcher) {
        return new HasSelectedOptionMatcher(optionMatcher);
    }
}
