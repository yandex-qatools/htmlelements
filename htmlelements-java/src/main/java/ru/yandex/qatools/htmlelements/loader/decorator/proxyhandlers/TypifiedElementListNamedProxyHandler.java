package ru.yandex.qatools.htmlelements.loader.decorator.proxyhandlers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class TypifiedElementListNamedProxyHandler<T extends TypifiedElement> implements InvocationHandler {
    private final Class<T> typifiedElementClass;
    private final ElementLocator locator;
    private final String name;

    public TypifiedElementListNamedProxyHandler(Class<T> typifiedElementClass, ElementLocator locator, String name) {
        this.typifiedElementClass = typifiedElementClass;
        this.locator = locator;
        this.name = name;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if ("toString".equals(method.getName())) {
            return name;
        }

        List<T> typifiedElements = new LinkedList<T>();
        List<WebElement> elements = locator.findElements();
        for (int i = 0; i < elements.size(); i++) {
            T typifiedElement = HtmlElementFactory.createTypifiedElementInstance(typifiedElementClass, elements.get(i));
            String typifiedElementName = String.format("%s [%d]", name, i);
            typifiedElement.setName(typifiedElementName);
            typifiedElements.add(typifiedElement);
        }

        try {
            return method.invoke(typifiedElements, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
