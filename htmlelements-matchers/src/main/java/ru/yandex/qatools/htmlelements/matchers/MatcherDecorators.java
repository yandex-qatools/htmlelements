package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder;
import ru.yandex.qatools.htmlelements.matchers.decorators.RefreshPageAction;
import ru.yandex.qatools.htmlelements.matchers.decorators.TimeoutWaiter;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.03.13
 */
// TODO Add JavaDoc
public final class MatcherDecorators {

    private MatcherDecorators() {
    }

    public static <T> MatcherDecoratorsBuilder<T> should(Matcher<? super T> matcher) {
        return MatcherDecoratorsBuilder.should(matcher);
    }

    public static RefreshPageAction pageRefresh(final WebDriver driver) {
        return RefreshPageAction.pageRefresh(driver);
    }

    public static TimeoutWaiter timeoutHasExpired() {
        return TimeoutWaiter.timeoutHasExpired();
    }

    public static TimeoutWaiter timeoutHasExpired(final long timeoutInMilliseconds) {
        return TimeoutWaiter.timeoutHasExpired(timeoutInMilliseconds);
    }
}
