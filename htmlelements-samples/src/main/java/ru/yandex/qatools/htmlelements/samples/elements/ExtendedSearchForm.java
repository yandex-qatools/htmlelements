package ru.yandex.qatools.htmlelements.samples.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Radio;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 18.02.13
 */
public class ExtendedSearchForm extends HtmlElement {
    @FindBy(name = "text")
    private TextInput requestInput;

    //...

    @FindBy(name = "zone")
    private Radio wordsPositionSelect;

    @FindBy(name = "wordforms")
    private Radio wordsFormSelect;

    //...
}
