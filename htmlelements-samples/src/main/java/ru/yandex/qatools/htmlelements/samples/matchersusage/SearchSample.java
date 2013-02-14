package ru.yandex.qatools.htmlelements.samples.matchersusage;

import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * User: lanwen
 * Date: 12/2/13, 3:13 PM
 */
public class SearchSample extends HtmlElement {

    @Override
    public String toString() {
        return "element with text: " + getText();
    }
}
