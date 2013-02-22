package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.matchers.decorators.RefreshMatcherDecorator;
import ru.yandex.qatools.htmlelements.matchers.decorators.WaitForMatcherDecorator;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 22.02.13
 */
public class MatcherDecorators {

    public static <T> WaitForMatcherDecorator<? super T> withWaitFor(Matcher<? super T> matcher) {
        return new WaitForMatcherDecorator<T>(matcher);
    }

    public static <T> Matcher<? super T> withPrerefresh(Matcher<? super T> matcher, WebDriver driver) {
        return new RefreshMatcherDecorator<T>(matcher, driver);
    }
}
