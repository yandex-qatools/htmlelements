package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class ActionMatcherDecorator<T> extends TypeSafeMatcher<T> {
    private final Matcher<? super T> matcher;
    private final Action action;

    private ActionMatcherDecorator(Matcher<? super T> matcher, Action action) {
        this.matcher = matcher;
        this.action = action;
    }

    @Override
    protected boolean matchesSafely(T item) {
        action.perform();
        return matcher.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher).appendText(" after ").appendDescriptionOf(action);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
        mismatchDescription.appendText(" after ").appendDescriptionOf(action);
    }

    @Factory
    public static <T> Matcher<T> decorateMatcherWithAction(final Matcher<? super T> matcher, final Action action) {
        return new ActionMatcherDecorator<>(matcher, action);
    }
}
