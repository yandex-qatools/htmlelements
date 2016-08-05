package ru.yandex.qatools.htmlelements.matchers.position;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Checks that element is intercepts borders on another.
 *
 * @author Pavel Zorin pazone@yandex-team.ru
 */

 public class IsIntercepts extends TypeSafeMatcher<HtmlElement> {

    private HtmlElement interceptor;

    public IsIntercepts(HtmlElement interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    protected boolean matchesSafely(HtmlElement item) {
        return new ElementPositionHelper(interceptor).isIntercepts(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("element is intercepts ");
    }

    @Override
    protected void describeMismatchSafely(HtmlElement item, Description mismatchDescription) {
        mismatchDescription.appendText(String.format(NOT_INTERCEPTS_PTRN, interceptor, item));
    }

    private static final String NOT_INTERCEPTS_PTRN = "Element '%s' is not intercepts the element '%s'. ";
}
