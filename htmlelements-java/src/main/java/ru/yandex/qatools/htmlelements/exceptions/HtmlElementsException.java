package ru.yandex.qatools.htmlelements.exceptions;

/**
 * Is used to be thrown in runtime in cases when a block of elements or a page object
 * can't be instantiated or initialized.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 16.09.12
 */
public class HtmlElementsException extends RuntimeException {
    public HtmlElementsException() {
        super();
    }

    public HtmlElementsException(String message) {
        super(message);
    }

    public HtmlElementsException(String message, Throwable cause) {
        super(message, cause);
    }

    public HtmlElementsException(Throwable cause) {
        super(cause);
    }
}
