package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;


public class TimeoutCondition implements Condition {
	private long start;
	private long timeoutInMilliseconds;
	private boolean started = false;
	
	public TimeoutCondition(long timeoutInMilliseconds) {
		this.timeoutInMilliseconds = timeoutInMilliseconds;
	}

	@Override
	public boolean isTrue() {
		if (!started) {
			start = System.currentTimeMillis();
			started = true;
		}
		return System.currentTimeMillis() < start + timeoutInMilliseconds;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("While waiting [").appendValue(timeoutInMilliseconds).appendText("] millis");
	}
}