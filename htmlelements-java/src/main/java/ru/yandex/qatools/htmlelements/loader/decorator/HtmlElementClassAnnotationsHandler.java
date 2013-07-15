package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandler;

/**
 * Handles {@link Block} annotation of {@link HtmlElement} and its successors.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 20.08.12
 */
public class HtmlElementClassAnnotationsHandler<T extends HtmlElement> extends AnnotationsHandler {
    private final Class<T> htmlElementClass;

    public HtmlElementClassAnnotationsHandler(Class<T> htmlElementClass) {
        this.htmlElementClass = htmlElementClass;
    }

    @Override
    public By buildBy() {
        Class<?> clazz = htmlElementClass;
        while (clazz != Object.class) {
            if (clazz.isAnnotationPresent(FindBy.class)) {
                return buildByFromFindBy(clazz.getAnnotation(FindBy.class));
            }
            clazz = clazz.getSuperclass();
        }

        throw new HtmlElementsException(String.format("Cannot determine how to locate instance of %s",
                htmlElementClass));
    }

    @Override
    public boolean shouldCache() {
        return false;
    }
}
