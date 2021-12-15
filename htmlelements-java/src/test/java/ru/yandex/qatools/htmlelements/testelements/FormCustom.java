package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Form;

import java.util.Collections;

/**
 * Represents user's custom form with ability to fill {@link TextInputCustom} elements
 * */
public class FormCustom extends Form {
    public FormCustom(WebElement wrappedElement) {
        super(wrappedElement, Collections.singletonList(
                new FillableOps(
                        TextInputCustom::new,
                        (WebElement e) -> "input".equals(e.getTagName())
                                && TextInputCustom.TYPE_ATTRIBUTE_VALUE.equals(e.getAttribute("type")))
        ));
    }
}
