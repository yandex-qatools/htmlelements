package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;

/**
 * Handles {@link Block} annotation of {@link HtmlElement} and its successors.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 20.08.12
 */
public class HtmlElementClassAnnotations<T extends HtmlElement> extends Annotations {
    private final Class<T> htmlElementClass;

    public HtmlElementClassAnnotations(Class<T> htmlElementClass) {
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
    public boolean isLookupCached() {
        return false;
    }
}
