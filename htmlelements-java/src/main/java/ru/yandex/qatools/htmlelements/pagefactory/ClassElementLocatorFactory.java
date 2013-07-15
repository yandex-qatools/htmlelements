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
public interface ClassElementLocatorFactory {

    ElementLocator createLocator(Class clazz);
}