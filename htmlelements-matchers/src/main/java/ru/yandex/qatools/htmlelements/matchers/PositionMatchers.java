package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.matchers.position.IsHigherThan;
import ru.yandex.qatools.htmlelements.matchers.position.IsInside;
import ru.yandex.qatools.htmlelements.matchers.position.IsIntercepts;
import ru.yandex.qatools.htmlelements.matchers.position.IsLowerThan;

/**
 * Factory of position matchers.
 *
 * @author Pavel Zorin <pazone@yandex-team.ru>
 */

public class PositionMatchers {

    public static Matcher<HtmlElement> higherThan(HtmlElement element) {
        return new IsHigherThan(element);
    }

    public static Matcher<HtmlElement> lowerThan(HtmlElement element) {
        return new IsLowerThan(element);
    }

    public static Matcher<HtmlElement> inside(HtmlElement element) {
        return new IsInside(element);
    }

    public static Matcher<HtmlElement> intercepts(HtmlElement element) {
        return new IsIntercepts(element);
    }
}
