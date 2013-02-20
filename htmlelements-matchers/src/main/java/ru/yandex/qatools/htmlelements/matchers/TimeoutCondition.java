package ru.yandex.qatools.htmlelements.matchers;

public class TimeoutCondition implements Condition {
	private long start;
	private long timeoutInMilliseconds;
	
	public TimeoutCondition(long timeoutInMilliseconds) {
		this.timeoutInMilliseconds = timeoutInMilliseconds;
		start();
	}
	
	public void start() {
		start = System.currentTimeMillis();
	}

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() < start + timeoutInMilliseconds;
	}
}