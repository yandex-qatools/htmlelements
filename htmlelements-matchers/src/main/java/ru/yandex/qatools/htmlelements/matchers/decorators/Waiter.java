package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 01.03.13
 */
public abstract class Waiter implements SelfDescribing {
    private static final long DEFAULT_POLLING_INTERVAL = SECONDS.toMillis(1);

    private long pollingInterval;
    private long startTime;

    protected Waiter() {
        pollingInterval = DEFAULT_POLLING_INTERVAL;
    }

    public void startWaiting() {
        startTime = System.currentTimeMillis();
    }

    public abstract boolean shouldStopWaiting();

    public long getPollingInterval() {
        return pollingInterval;
    }

    public Waiter withPollingInterval(long pollingInterval) {
        setPollingInterval(pollingInterval);
        return this;
    }

    protected long getStartTime() {
        return startTime;
    }

    protected void setPollingInterval(long pollingInterval) {
        this.pollingInterval = pollingInterval;
    }
}
