package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Select;

import java.util.List;

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
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        if (selectedOptions.isEmpty()) {
            return false;
        }
        return optionMatcher.matches(selectedOptions.get(0));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("selected option ").appendDescriptionOf(optionMatcher);
    }

    @Override
    protected void describeMismatchSafely(Select select, Description mismatchDescription) {
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        if (selectedOptions.isEmpty()) {
            mismatchDescription.
                    appendValue(select).
                    appendText(" had no selected options");
        } else {
            Description selectedOptionMismatchDescription =  new StringDescription();
            optionMatcher.describeMismatch(selectedOptions.get(0), selectedOptionMismatchDescription);
            mismatchDescription.
                    appendText("selected option of ").
                    appendValue(select).
                    appendText(" was ").
                    appendText(selectedOptionMismatchDescription.toString());
        }
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
