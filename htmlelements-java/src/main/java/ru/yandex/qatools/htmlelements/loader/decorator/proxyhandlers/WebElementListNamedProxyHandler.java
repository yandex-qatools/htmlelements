package ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class WebElementListNamedProxyHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final String name;

    public WebElementListNamedProxyHandler(ElementLocator locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }

        List<WebElement> elements = locator.findElements();

        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
