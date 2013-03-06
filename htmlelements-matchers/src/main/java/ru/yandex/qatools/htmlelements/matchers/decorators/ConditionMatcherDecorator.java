package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.*;
import org.junit.internal.AssumptionViolatedException;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class ConditionMatcherDecorator<T> extends TypeSafeMatcher<T> {
    private final Matcher<? super T> matcher;
    private final Condition condition;

    private ConditionMatcherDecorator(Matcher<? super T> matcher, Condition condition) {
        this.matcher = matcher;
        this.condition = condition;
    }

    @Override
    protected boolean matchesSafely(T item) {
        if (condition.isSatisfied()) {
            return matcher.matches(item);
        }

        Description conditionDescription = new StringDescription();
        condition.describeTo(conditionDescription);
        throw new AssumptionViolatedException(String.format("Condition is not satisfied: %s", conditionDescription));
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher).appendText(" if ").appendDescriptionOf(condition);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
        mismatchDescription.appendText(" if ").appendDescriptionOf(condition);
    }

    @Factory
    public static <T> Matcher<T> decorateMatcherWithCondition(Matcher<? super T> matcher, Condition condition) {
        return new ConditionMatcherDecorator<T>(matcher, condition);
    }
}
