package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public abstract class BlockMatchers {

    public static Matcher<Class<? extends HtmlElement>> blockSubElementsExists(WebDriver webDriver) {
        return new BlockFieldExistsMatcher(webDriver);
    }

    public static Matcher<HtmlElement> subElementsExists(WebDriver webDriver) {
        return new ElementFieldsExistsMatcher(webDriver);
    }
}
