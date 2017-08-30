package ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.SystemClock;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class WebElementNamedProxyHandler extends LocatingElementHandler {

    public static final int DEFAULT_TIMEOUT = 5;

    private final long timeOutInSeconds;

    private final Clock clock;

    private final String name;

    public WebElementNamedProxyHandler(ElementLocator locator, String name) {
        super(locator);
        this.name = name;
        this.clock = new SystemClock();
        this.timeOutInSeconds = Integer.getInteger("webdriver.timeouts.implicitlywait", DEFAULT_TIMEOUT);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }

        final long end = this.clock.laterBy(TimeUnit.SECONDS.toMillis(this.timeOutInSeconds));

        StaleElementReferenceException lastException;
        do {
            try {
                return super.invoke(o, method, objects);
            } catch (StaleElementReferenceException e) {
                lastException = e;
                this.waitFor();
            }
        } while (this.clock.isNowBefore(end));
        throw lastException;
    }

    protected long sleepFor() {
        return 500L;
    }

    private void waitFor() throws InterruptedException {
        Thread.sleep(this.sleepFor());
    }
}
