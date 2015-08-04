package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * A factory for producing {@link ElementLocator}s. It is expected that a new ElementLocator will be
 * returned per call.
 *
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public interface CustomElementLocatorFactory extends ElementLocatorFactory {
    ElementLocator createLocator(Class clazz);
}
