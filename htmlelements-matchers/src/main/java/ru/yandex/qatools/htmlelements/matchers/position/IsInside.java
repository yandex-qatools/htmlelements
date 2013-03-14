package ru.yandex.qatools.htmlelements.matchers.position;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class IsInside extends TypeSafeMatcher<HtmlElement> {

    private HtmlElement inside;

    public IsInside(HtmlElement inside) {
        this.inside = inside;
    }

    @Override
    protected boolean matchesSafely(HtmlElement item) {
        return new ElementPositionHelper(inside).isElementInside(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is inside ");
    }

    @Override
    protected void describeMismatchSafely(HtmlElement item, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("Element '%s' is not inside the element '%s'. ", inside, item));
    }
}
