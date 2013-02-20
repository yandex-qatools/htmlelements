package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;


public class TimeoutCondition implements Condition {
	private long start;
	private long timeoutInMilliseconds;
	
	public TimeoutCondition(long timeoutInMilliseconds) {
		this.timeoutInMilliseconds = timeoutInMilliseconds;
	}
	
	public void start() {
		start = System.currentTimeMillis();
	}

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() < start + timeoutInMilliseconds;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("While waiting [").appendValue(timeoutInMilliseconds).appendText("] millis");
	}
}