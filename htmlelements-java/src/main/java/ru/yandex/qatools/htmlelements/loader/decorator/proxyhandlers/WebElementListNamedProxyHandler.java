package ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.Method;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class WebElementListNamedProxyHandler extends LocatingElementListHandler {
    private final String name;

    public WebElementListNamedProxyHandler(ElementLocator locator, String name) {
        super(locator);
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }
        return super.invoke(o, method, objects);
    }
}
