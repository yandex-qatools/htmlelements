package ru.yandex.qatools.htmlelements.loader.decorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers.HtmlElementListNamedProxyHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers.TypifiedElementListNamedProxyHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers.WebElementListNamedProxyHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers.WebElementNamedProxyHandler;

/**
 * Contains factory methods for creating instances of blocks, typified elements, page objects
 * and for creating lazy proxies.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 16.08.12
 */
public class HtmlElementFactory {

    public static <T extends HtmlElement> T createHtmlElementInstance(Class<T> elementClass) {
        try {
            return elementClass.newInstance();
        } catch (InstantiationException e) {
            throw new HtmlElementsException(e);
        } catch (IllegalAccessException e) {
            throw new HtmlElementsException(e);
        }
    }

    public static <T extends TypifiedElement> T createTypifiedElementInstance(Class<T> elementClass,
                                                                              WebElement elementToWrap) {
        try {
            Constructor<T> constructor = elementClass.getConstructor(WebElement.class);
            return constructor.newInstance(elementToWrap);
        } catch (NoSuchMethodException e) {
            throw new HtmlElementsException(e);
        } catch (InstantiationException e) {
            throw new HtmlElementsException(e);
        } catch (IllegalAccessException e) {
            throw new HtmlElementsException(e);
        } catch (InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

    public static <T> T createPageObjectInstance(Class<T> pageObjectClass, ElementLocatorFactory locatorFactory) {
        try {
            try {
                Constructor<T> constructor = pageObjectClass.getConstructor(ElementLocatorFactory.class);
                return constructor.newInstance(locatorFactory);
            } catch (NoSuchMethodException e) {
                return pageObjectClass.newInstance();
            }
        } catch (InstantiationException e) {
            throw new HtmlElementsException(e);
        } catch (IllegalAccessException e) {
            throw new HtmlElementsException(e);
        } catch (InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

    public static WebElement createProxyForWebElement(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementHandler(locator);
        return (WebElement) Proxy.newProxyInstance(loader,
                new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
    }

    public static WebElement createNamedProxyForWebElement(ClassLoader loader, ElementLocator locator, String name) {
        InvocationHandler handler = new WebElementNamedProxyHandler(locator, name);
        return (WebElement) Proxy.newProxyInstance(loader,
                new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
    }

    @SuppressWarnings("unchecked")
    public static List<WebElement> createNamedProxyForWebElementList(ClassLoader loader, ElementLocator locator,
                                                                     String name) {
        InvocationHandler handler = new WebElementListNamedProxyHandler(locator, name);
        return (List<WebElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    @SuppressWarnings("unchecked")
    public static <T extends TypifiedElement> List<T> createNamedProxyForTypifiedElementList(Class<T> elementClass,
                                                                                             ClassLoader loader, ElementLocator locator, String name) {
        InvocationHandler handler = new TypifiedElementListNamedProxyHandler<T>(elementClass, locator, name);
        return (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    @SuppressWarnings("unchecked")
    public static <T extends HtmlElement> List<T> createNamedProxyForHtmlElementList(Class<T> elementClass,
                                                                                     ClassLoader loader, ElementLocator locator, String name) {
        InvocationHandler handler = new HtmlElementListNamedProxyHandler<T>(elementClass, locator, name);
        return (List<T>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
