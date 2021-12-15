package ru.yandex.qatools.htmlelements.element;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Optional;

/**
 * Represents text input control (such as &lt;input type="text"/&gt; or &lt;textarea/&gt;).
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class TextInput extends TypifiedElement implements Fillable {
    /**
     * Specifies wrapped {@link WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public TextInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * @return Text entered into the text input.
     * @deprecated Use getText() instead.
     * <p/>
     * Retrieves the text entered into this text input.
     */
    @Deprecated
    public String getEnteredText() {
        return getText();
    }

    /**
     * Retrieves the text entered into this text input.
     *
     * @return Text entered into the text input.
     */
    @Override
    public String getText() {
        if ("textarea".equals(getWrappedElement().getTagName())) {
            return getWrappedElement().getText();
        }

        return Optional.ofNullable(getWrappedElement().getAttribute("value")).orElse("");
    }

    /**
     * Returns sequence of backspaces and deletes that will clear element.
     * clear() can't be used because generates separate onchange event
     * See https://github.com/yandex-qatools/htmlelements/issues/65
     */
    public String getClearCharSequence() {
        return StringUtils.repeat(Keys.DELETE.toString() + Keys.BACK_SPACE, getText().length());
    }

    @Override
    public void fill(String value) {
        sendKeys(getClearCharSequence() + value);
    }
}
