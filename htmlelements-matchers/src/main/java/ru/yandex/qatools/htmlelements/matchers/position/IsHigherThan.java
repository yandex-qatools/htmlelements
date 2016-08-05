package ru.yandex.qatools.htmlelements.matchers.position;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Checks that one element is absolutely higher then other.
 *
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class IsHigherThan extends TypeSafeMatcher<HtmlElement> {

    private HtmlElement higher;

    public IsHigherThan(HtmlElement higher) {
        this.higher = higher;
    }

    @Override
    protected boolean matchesSafely(HtmlElement item) {
        return new ElementPositionHelper(higher).isHigher(new ElementPositionHelper(item));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is higher ");
    }

    @Override
    protected void describeMismatchSafely(HtmlElement item, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("Element '%s' is not higher than element '%s'. ", higher, item));
    }

    @SuppressWarnings("unused")
    @Factory
    public static IsHigherThan isHigherThan(HtmlElement element) {
        return new IsHigherThan(element);
    }


}
