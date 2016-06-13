package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.*;
import org.junit.AssumptionViolatedException;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class ConditionMatcherDecorator<T, E> extends TypeSafeMatcher<T> {
    private final Matcher<? super T> matcher;
    private final E itemToMatchCondition;
    private final Matcher<? super E> condition;

    public ConditionMatcherDecorator(Matcher<? super T> matcher, E itemToMatchCondition, Matcher<? super E> condition) {
        this.matcher = matcher;
        this.itemToMatchCondition = itemToMatchCondition;
        this.condition = condition;
    }

    @Override
    protected boolean matchesSafely(T item) {
        if (condition.matches(itemToMatchCondition)) {
            return matcher.matches(item);
        }

        Description conditionDescription =
                new StringDescription()
                        .appendValue(itemToMatchCondition)
                        .appendDescriptionOf(condition);

        throw new AssumptionViolatedException(
                String.format("Condition is not satisfied: %s", conditionDescription));
    }

    @Override
    public void describeTo(Description description) {
        description
                .appendDescriptionOf(matcher)
                .appendText(" if ")
                .appendDescriptionOf(condition);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    @Factory
    public static <T, E> Matcher<T> decorateMatcherWithCondition(final Matcher<? super T> matcher,
                                                                 final E itemToMatchCondition,
                                                                 final Matcher<? super E> condition) {
        return new ConditionMatcherDecorator<>(matcher, itemToMatchCondition, condition);
    }
}
