package ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class WebElementNamedProxyHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final String name;

    public WebElementNamedProxyHandler(ElementLocator locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }

        WebElement element = locator.findElement();

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }

        try {
            return method.invoke(element, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
