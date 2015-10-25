package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 01.03.13
 */
public class WaiterMatcherDecorator<T> extends TypeSafeMatcher<T> {
    private final Matcher<? super T> matcher;
    private final Waiter waiter;

    private WaiterMatcherDecorator(Matcher<? super T> matcher, Waiter waiter) {
        this.matcher = matcher;
        this.waiter = waiter;
    }

    @Override
    protected boolean matchesSafely(T item) {
        waiter.startWaiting();

        while (!waiter.shouldStopWaiting()) {
            if (matcher.matches(item)) {
                return true;
            }

            try {
                Thread.sleep(waiter.getPollingInterval());
            } catch (InterruptedException e) {
                break;
            }
        }

        return matcher.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher).appendText(" while waiting for ").appendDescriptionOf(waiter);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
        mismatchDescription.appendText(" while waiting for ").appendDescriptionOf(waiter);
    }

    @Factory
    public static <T> Matcher<T> decorateMatcherWithWaiter(final Matcher<? super T> matcher, final Waiter waiter) {
        return new WaiterMatcherDecorator<>(matcher, waiter);
    }
}
