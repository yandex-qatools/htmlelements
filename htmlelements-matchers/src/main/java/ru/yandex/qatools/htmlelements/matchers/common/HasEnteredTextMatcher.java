package ru.yandex.qatools.htmlelements.matchers.common;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.TextInput;

import static org.hamcrest.Matchers.is;

/**
 * Matches entered in {@link TextInput} text with specified matcher.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.09.12
 */
public class HasEnteredTextMatcher extends TypeSafeMatcher<TextInput> {
    private final Matcher<String> textMatcher;

    public HasEnteredTextMatcher(Matcher<String> textMatcher) {
        this.textMatcher = textMatcher;
    }

    @Override
    protected boolean matchesSafely(TextInput textInput) {
        return textMatcher.matches(textInput.getText());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("text entered in text input ").appendDescriptionOf(textMatcher);
    }

    @Override
    protected void describeMismatchSafely(TextInput textInput, Description mismatchDescription) {
        mismatchDescription.appendText("text entered in text input ").
                appendValue(textInput).
                appendText(" not ").
                appendDescriptionOf(textMatcher);
    }

    /**
     * Creates matcher that matches text entered in {@link TextInput} with specified matcher.
     *
     * @param textMatcher Matcher to match entered text with.
     */
    @Factory
    public static Matcher<TextInput> hasEnteredText(final Matcher<String> textMatcher) {
        return new HasEnteredTextMatcher(textMatcher);
    }

    /**
     * Creates matcher that checks if text entered in {@link TextInput} equals to given text.
     *
     * @param text Expected entered text.
     */
    @Factory
    public static Matcher<TextInput> hasEnteredText(final String text) {
        return new HasEnteredTextMatcher(is(text));
    }
}
