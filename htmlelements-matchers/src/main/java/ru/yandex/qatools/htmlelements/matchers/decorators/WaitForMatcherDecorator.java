package ru.yandex.qatools.htmlelements.matchers.decorators;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


/**
 * User: lanwen
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
		public void describeTo(Description description) {}
		
		@Override
		public boolean isTrue() {
			return true;
		}
	};

	public WaitForMatcherDecorator(Matcher<? super T> matcher) {
		this.matcher = matcher;
		this.timeOutCondition = new TimeoutCondition(DEFAULT_TIMEOUT);
		this.intervalInMilliseconds = DEFAULT_INTERVAL;
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
		return new WaitForMatcherDecorator<T>(matcher);
	}

	public WaitForMatcherDecorator<T> orUntil(final Condition condition) {
		final Condition oldCondition = this.condition;
		Condition newCondition = new Condition() {
			
			@Override
			public void describeTo(Description description) {
				description.appendDescriptionOf(oldCondition)
								.appendText(" or ")
								.appendDescriptionOf(condition);
			}
			
			@Override
			public boolean isTrue() {
				return oldCondition.isTrue() && condition.isTrue();
			}
		};
		
		this.condition = newCondition;
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