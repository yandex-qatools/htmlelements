package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: lanwen
 * Date: 23.01.15
 * Time: 12:45
 */
@FindBy(css = DummyBlock.DUMMY_SELECTOR)
public class DummyBlock extends HtmlElement {

    public static final String DUMMY_SELECTOR = ".blablabla";

}
