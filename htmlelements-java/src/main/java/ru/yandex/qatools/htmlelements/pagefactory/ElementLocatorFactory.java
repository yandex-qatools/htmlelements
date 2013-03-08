package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.AnnotatedElement;

/**
 * @author Artem Eroshenko eroshenkoam
 *         3/8/13, 10:58 PM
 */
public interface ElementLocatorFactory extends org.openqa.selenium.support.pagefactory.ElementLocatorFactory {

    public ElementLocator createLocator(AnnotatedElement annotated);

}
