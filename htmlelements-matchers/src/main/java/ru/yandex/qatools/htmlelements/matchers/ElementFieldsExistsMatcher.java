package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Checks that all declared elements of block's instance are present on page.
 *
 * @author Pavel Zorin pazone@yandex-team.ru
 */

class ElementFieldsExistsMatcher extends TypeSafeMatcher<HtmlElement> {

    private BlockFieldExistsMatcher baseMatcher;

    public ElementFieldsExistsMatcher(WebDriver webDriver) {
        this.baseMatcher = new BlockFieldExistsMatcher(webDriver);
    }

    @Override
    protected boolean matchesSafely(HtmlElement item) {
        return baseMatcher.matchesSafely(item.getClass());
    }

    @Override
    public void describeTo(Description description) {
        baseMatcher.describeTo(description);
    }

    @Factory
    public static Matcher elementFieldsExists(WebDriver webDriver) {
        return new ElementFieldsExistsMatcher(webDriver);
    }
}
