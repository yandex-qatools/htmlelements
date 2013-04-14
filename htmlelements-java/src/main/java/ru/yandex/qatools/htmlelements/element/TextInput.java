package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.WebElement;

/**
 * Represents text input control (such as &lt;input type="text"/&gt; or &lt;textarea/&gt;).
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class TextInput extends TypifiedElement {
    /**
     * Specifies wrapped {@link WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public TextInput(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Clears all the text entered into this text input.
     */
    public void clear() {
        getWrappedElement().clear();
    }

    /**
     * Prints specified char sequence into this text input.
     *
     * @param keys Text to print.
     */
    public void sendKeys(CharSequence... keys) {
        getWrappedElement().sendKeys(keys);
    }

    /**
     * @return Text entered into the text input.
     * @deprecated Use getText() instead.
     *             <p/>
     *             Retrieves the text entered into this text input.
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
    public String getText() {
        if ("textarea".equals(getWrappedElement().getTagName())) {
            return getWrappedElement().getText();
        }

        String enteredText = getWrappedElement().getAttribute("value");
        if (enteredText == null) {
            return "";
        }
        return enteredText;
    }
}
