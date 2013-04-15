package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder;
import ru.yandex.qatools.htmlelements.matchers.decorators.RefreshPageAction;
import ru.yandex.qatools.htmlelements.matchers.decorators.TimeoutWaiter;

/**
 * Contains factory methods for matchers decorators usage.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.03.13
 */
public final class MatcherDecorators {

    private MatcherDecorators() {
    }

    /**
     * Factory method for decorating matcher with action, condition or waiter.
     *
     * @param matcher Matcher to be decorated.
     */
    public static <T> MatcherDecoratorsBuilder<T> should(final Matcher<? super T> matcher) {
        return MatcherDecoratorsBuilder.should(matcher);
    }

    /**
     * Returns instance of {@link RefreshPageAction}.
     */
    public static RefreshPageAction pageRefresh(final WebDriver driver) {
        return RefreshPageAction.pageRefresh(driver);
    }

    /**
     * Returns instance of {@link TimeoutWaiter}.
     */
    public static TimeoutWaiter timeoutHasExpired() {
        return TimeoutWaiter.timeoutHasExpired();
    }

    /**
     * Returns instance of {@link TimeoutWaiter} with specified timeout duration.
     */
    public static TimeoutWaiter timeoutHasExpired(final long timeoutInMilliseconds) {
        return TimeoutWaiter.timeoutHasExpired(timeoutInMilliseconds);
    }
}
