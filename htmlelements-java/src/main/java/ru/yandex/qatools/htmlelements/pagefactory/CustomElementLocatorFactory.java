package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

/**
 * A factory for producing {@link ElementLocator}s. It is expected that a new ElementLocator will be
 * returned per call.
 *
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public abstract class CustomElementLocatorFactory implements ElementLocatorFactory {
    /**
     * When a field on a class needs to be decorated with an {@link ElementLocator} this method will
     * be called.
     *
     * @param field Filed to be decorated.
     * @return An ElementLocator object.
     */
    public abstract ElementLocator createLocator(Field field);

    public abstract ElementLocator createLocator(Class clazz);

    public ElementLocator createLocator(AnnotatedElement annotated) {
        if (annotated instanceof Field) {
            return createLocator((Field) annotated);
        }

        if (annotated instanceof Class) {
            return createLocator((Class) annotated);
        }

        throw new RuntimeException();
    }
}