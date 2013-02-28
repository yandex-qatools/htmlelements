package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class ConditionMatcherDecorator<T> extends TypeSafeMatcher<T> {
    private final Matcher<? super T> matcher;
    private final Condition condition;

    public ConditionMatcherDecorator(Matcher<? super T> matcher, Condition condition) {
        this.matcher = matcher;
        this.condition = condition;
    }

    @Override
    protected boolean matchesSafely(T item) {
        if (condition.isSatisfied()) {
            return matcher.matches(item);
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher).appendText(" if ").appendDescriptionOf(condition);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
        mismatchDescription.appendText(" after ").appendDescriptionOf(condition);
    }
}
