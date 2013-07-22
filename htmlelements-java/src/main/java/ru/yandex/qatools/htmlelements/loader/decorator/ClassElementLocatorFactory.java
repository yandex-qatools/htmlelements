package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;


/**
 * A factory for producing {@link ElementLocator}s for class. 
 * It is expected that a new ElementLocator will be returned per call.
 *
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public interface ClassElementLocatorFactory extends ElementLocatorFactory {

    ElementLocator createLocator(Class clazz);
}