package ru.yandex.qatools.htmlelements.matchers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.mockito.Matchers;

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
    
    private TimeoutCondition timeOutCondition;
    private Condition condition = new Condition() {
		@Override
		public boolean isTrue() {
			return true;
		}
	};
    
    public WaitForMatcherDecorator(Matcher<? super T> matcher,
    								TimeoutCondition timeoutCondition,
    								long intervalInMilliseconds) {
    	this.matcher = matcher;
    	this.timeOutCondition = timeoutCondition;
    	this.intervalInMilliseconds = intervalInMilliseconds;
    }

    @Override
    protected boolean matchesSafely(T item) {
    	timeOutCondition.start();
        while (timeOutCondition.isTrue() && condition.isTrue()) {
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
    public static <T> WaitForMatcherDecorator<? super T> withWaitFor(Matcher<? super T> matcher) {
        return withWaitFor(matcher, DEFAULT_TIMEOUT, DEFAULT_INTERVAL);
    }

    @Factory
    public static <T> WaitForMatcherDecorator<? super T> withWaitFor(Matcher<? super T> matcher, long timeoutInMilliseconds) {
        return withWaitFor(matcher, timeoutInMilliseconds, DEFAULT_INTERVAL);
    }

    @Factory
    public static <T> WaitForMatcherDecorator<? super T> withWaitFor(Matcher<? super T> matcher,
                                             final long timeoutInMilliseconds,
                                             long intervalInMilliseconds) {
    	TimeoutCondition timeOutCondition = new TimeoutCondition(timeoutInMilliseconds);
        return new WaitForMatcherDecorator<T>(matcher, timeOutCondition, intervalInMilliseconds);
    }
    
    public WaitForMatcherDecorator<T> orUntil(Condition condition) {
		this.condition = condition;
		return this;
    }
    
    public WaitForMatcherDecorator<T> withTimeout(final long timeoutInMilliseconds) {
		this.timeOutCondition = new TimeoutCondition(timeoutInMilliseconds);
		return this;
    }

	public WaitForMatcherDecorator<T> withInterval(long intervalInMilliseconds) {
		this.intervalInMilliseconds = intervalInMilliseconds;
		return this;
	}
}