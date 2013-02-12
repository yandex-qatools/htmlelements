package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

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
     * @param field
     * @return An ElementLocator object.
     */
    public ElementLocator createLocator(Field field) {
        return null;
    }

    public ElementLocator createLocator(Class clazz) {
        return null;
    }
}