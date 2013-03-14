package ru.yandex.qatools.htmlelements.matchers.position;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class IsLowerThan extends TypeSafeMatcher<HtmlElement> {

    private HtmlElement lower;

    public IsLowerThan(HtmlElement lower) {
        this.lower = lower;
    }

    @Override
    protected boolean matchesSafely(HtmlElement item) {
        return new ElementPositionHelper(lower).isLower(new ElementPositionHelper(item));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is lower ");
    }

    @Override
    protected void describeMismatchSafely(HtmlElement item, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("Element '%s' is not lower than element '%s'. ", lower, item));
    }
}
