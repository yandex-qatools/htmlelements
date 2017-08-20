package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBy.FindByBuilder;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;

/**
 * Handles annotation of {@link HtmlElement} and its successors.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 20.08.12
 */
public class HtmlElementClassAnnotationsHandler<T extends HtmlElement> extends AbstractAnnotations {
    private final Class<T> elementClass;

    public HtmlElementClassAnnotationsHandler(Class<T> elementClass) {
        this.elementClass = elementClass;
    }

    @Override
    public By buildBy() {
        Class<?> clazz = elementClass;
        while (clazz != Object.class) {
            if (clazz.isAnnotationPresent(FindBy.class)) {
                return new FindByBuilder().buildIt(clazz.getAnnotation(FindBy.class), null);
            }
            clazz = clazz.getSuperclass();
        }

        throw new HtmlElementsException(String.format("Cannot determine how to locate instance of %s", elementClass));
    }

    @Override
    public boolean isLookupCached() {
        return false;
    }
}
