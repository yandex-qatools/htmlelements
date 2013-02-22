package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Radio;

/**
 * Checks that {@link Radio} has selected button matching the specified matcher.
 * In case select has not selected button this matcher will also return false.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.09.12
 */
public class HasSelectedRadioButtonMatcher extends TypeSafeMatcher<Radio> {
    private Matcher<WebElement> matcher;

    public HasSelectedRadioButtonMatcher(Matcher<WebElement> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(Radio radio) {
        try {
            return matcher.matches(radio.getSelectedButton());
        } catch (NoSuchElementException e) {
            // Process case if radio has not selected button
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("radio has selected button ");
    }

    @Override
    protected void describeMismatchSafely(Radio radio, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("radio '%s' has not selected button ", radio));
    }

    /**
     * Creates matcher that tests if {@link Radio} has selected button matching the specified matcher.
     *
     * @param buttonMatcher Matcher to match selected radio button with.
     */
    @Factory
    public static Matcher<Radio> hasSelectedRadioButton(Matcher<WebElement> buttonMatcher) {
        return new HasSelectedRadioButtonMatcher(buttonMatcher);
    }
}
