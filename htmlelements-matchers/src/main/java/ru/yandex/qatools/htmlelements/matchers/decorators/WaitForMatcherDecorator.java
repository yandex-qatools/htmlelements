package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * User: lanwen
 * Date: 19.12.12
 * Time: 14:18
 * <p/>
 * Usage example: assertThat(onHomePage().getAbookTab(), withWaitFor(exists()));
 */

public class WaitForMatcherDecorator<T> extends TypeSafeMatcher<T> {

    public static final long DEFAULT_INTERVAL = MILLISECONDS.toMillis(500);
    public static final long DEFAULT_TIMEOUT = SECONDS.toMillis(30);

    private Matcher<? super T> matcher;

    private long timeoutInMilliseconds;
    private long intervalInMilliseconds;

    public WaitForMatcherDecorator(Matcher<? super T> matcher,
                                   long timeoutInMilliseconds,
                                   long intervalInMilliseconds) {
        this.matcher = matcher;
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        this.intervalInMilliseconds = intervalInMilliseconds;
    }

    @Override
    protected boolean matchesSafely(T item) {
        long start = System.currentTimeMillis();
        long end = start + timeoutInMilliseconds;
        while (System.currentTimeMillis() < end) {
            if (matcher.matches(item)) {
                return true;
            }
            try {
                Thread.sleep(intervalInMilliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return matcher.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("While waiting [").appendValue(timeoutInMilliseconds)
                .appendText("] millis it should be: ")
                .appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    @Factory
    public static <T> Matcher<? super T> withWaitFor(Matcher<? super T> matcher) {
        return new WaitForMatcherDecorator<T>(matcher, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
    }


    @Factory
    public static <T> Matcher<? super T> withWaitFor(Matcher<? super T> matcher, long timeoutInMilliseconds) {
        return new WaitForMatcherDecorator<T>(matcher, timeoutInMilliseconds, DEFAULT_INTERVAL);
    }


    @Factory
    public static <T> Matcher<? super T> withWaitFor(Matcher<? super T> matcher,
                                             long timeoutInMilliseconds,
                                             long intervalInMilliseconds) {
        return new WaitForMatcherDecorator<T>(matcher, timeoutInMilliseconds, intervalInMilliseconds);
    }


    @Factory
    public static <T> Matcher<? super T> withWaitFor(Matcher<? super T> matcher, Interrupter interrupter) {
        if (interrupter.isInterrupted()) {
            return matcher;
        }
        return new WaitForMatcherDecorator<T>(matcher, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
    }

    @Factory
    public static <T> Matcher<? super T> withWaitFor(Matcher<? super T> matcher, Interrupter interrupter,
                                                       long timeoutInMilliseconds) {
        if (interrupter.isInterrupted()) {
            return matcher;
        }
        return new WaitForMatcherDecorator<T>(matcher, timeoutInMilliseconds, DEFAULT_INTERVAL);
    }

    @Factory
    public static <T> Matcher<? super T> withWaitFor(Matcher<? super T> matcher,
                                                       Interrupter interrupter,
                                                       long timeoutInMilliseconds,
                                                       long intervalInMilliseconds) {
        if (interrupter.isInterrupted()) {
            return matcher;
        }
        return new WaitForMatcherDecorator<T>(matcher, timeoutInMilliseconds, intervalInMilliseconds);
    }


}