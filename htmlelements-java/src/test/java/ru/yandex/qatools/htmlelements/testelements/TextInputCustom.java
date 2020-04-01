package ru.yandex.qatools.htmlelements.testelements;

import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.Fillable;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Text input, that for some reasons has custom filling logic.
 * */
public class TextInputCustom extends TextInput implements Fillable {
    public static final String TYPE_ATTRIBUTE_VALUE ="its_a_custom_input_block_in_form";

    /**
     * Specifies wrapped {@link WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public TextInputCustom(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void fill(String value) {
        getWrappedElement().click();
        getWrappedElement().sendKeys(value);
    }
}
