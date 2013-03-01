package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 01.03.13
 */
public class TimeoutWaiter extends Waiter {
    private static final long DEFAULT_TIMEOUT = SECONDS.toMillis(30);

    private final long timeout;

    public TimeoutWaiter() {
        this.timeout = DEFAULT_TIMEOUT;
    }

    public TimeoutWaiter(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean shouldStopWaiting() {
        long currentTime = System.currentTimeMillis();
        return currentTime >= getStartTime() + timeout;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(timeout).appendText(" milliseconds");
    }
}
