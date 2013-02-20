package ru.yandex.qatools.htmlelements.matchers.decorators;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


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

    private long intervalInMilliseconds;
    
    private TimeoutCondition timeOutCondition;
    private Condition condition = new Condition() {
		@Override
		public boolean isTrue() {
			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("true");
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
        description.appendDescriptionOf(timeOutCondition)
        		.appendText(" and ")
        		.appendDescriptionOf(condition)
                .appendText(" it should be: ")
                .appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    @Factory
    public static <T> WaitForMatcherDecorator<? super T> withWaitFor(Matcher<? super T> matcher) {
    	return new WaitForMatcherDecorator<T>(matcher, 
    			new TimeoutCondition(DEFAULT_TIMEOUT), 
    			DEFAULT_INTERVAL);
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