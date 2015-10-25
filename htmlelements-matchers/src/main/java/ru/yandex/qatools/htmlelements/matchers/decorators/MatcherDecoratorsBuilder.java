package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static ru.yandex.qatools.htmlelements.matchers.decorators.ActionMatcherDecorator.decorateMatcherWithAction;
import static ru.yandex.qatools.htmlelements.matchers.decorators.ConditionMatcherDecorator.decorateMatcherWithCondition;
import static ru.yandex.qatools.htmlelements.matchers.decorators.WaiterMatcherDecorator.decorateMatcherWithWaiter;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class MatcherDecoratorsBuilder<T> extends TypeSafeMatcher<T> {
    private Matcher<? super T> matcher;

    @Factory
    public static <T> MatcherDecoratorsBuilder<T> should(final Matcher<? super T> matcher) {
        return new MatcherDecoratorsBuilder<>(matcher);
    }

    private MatcherDecoratorsBuilder(Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(T item) {
        return matcher.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        matcher.describeTo(description);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    public MatcherDecoratorsBuilder<T> after(final Action action) {
        matcher = decorateMatcherWithAction(matcher, action);
        return this;
    }

    public <E> MatcherDecoratorsBuilder<T> inCase(final E itemToMatchCondition, final Matcher<? super E> condition) {
        matcher = decorateMatcherWithCondition(matcher, itemToMatchCondition, condition);
        return this;
    }

    public MatcherDecoratorsBuilder<T> whileWaitingUntil(final Waiter waiter) {
        matcher = decorateMatcherWithWaiter(matcher, waiter);
        return this;
    }
}
