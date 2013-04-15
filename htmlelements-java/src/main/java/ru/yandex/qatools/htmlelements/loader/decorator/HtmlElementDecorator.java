package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.lang.reflect.Field;
import java.util.List;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.*;

/**
 * Decorator which is used to decorate fields of blocks and page objects.
 * <p/>
 * Will decorate the following fields with lazy proxies:
 * <p/>
 * <ul>
 * <li>{@code WebElement}</li>
 * <li>{@code List<WebElement>}</li>
 * <li>Block of elements ({@link HtmlElement} and its successors)</li>
 * <li>List of blocks</li>
 * <li>Typified element ({@link ru.yandex.qatools.htmlelements.element.TypifiedElement} successors)</li>
 * <li>List of typified elements</li>
 * </ul>
 * <p/>
 * {@code WebElements}, lists of {@code WebElements}, typified elements and lists of typified elements
 * have to be marked with {@link org.openqa.selenium.support.FindBy} or {@link org.openqa.selenium.support.FindBys}
 * annotation. For blocks and lists of blocks it is not obligatory: corresponding
 * {@link ru.yandex.qatools.htmlelements.annotations.Block} annotation will be processed if there are no
 * {@link org.openqa.selenium.support.FindBy} or {@link org.openqa.selenium.support.FindBys} annotation.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class HtmlElementDecorator extends DefaultFieldDecorator {
    public HtmlElementDecorator(ElementLocatorFactory locatorFactory) {
        super(locatorFactory);
    }

    public HtmlElementDecorator(SearchContext searchContext) {
        this(new HtmlElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!isDecoratableField(field)) {
            return null;
        }

        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        String elementName = getElementName(field);

        if (isTypifiedElement(field)) {
            @SuppressWarnings("unchecked")
            Class<TypifiedElement> typifiedElementClass = (Class<TypifiedElement>) field.getType();
            return decorateTypifiedElement(typifiedElementClass, loader, locator, elementName);
        } else if (isHtmlElement(field)) {
            @SuppressWarnings("unchecked")
            Class<HtmlElement> htmlElementClass = (Class<HtmlElement>) field.getType();
            return decorateHtmlElement(htmlElementClass, loader, locator, elementName);
        } else if (isWebElement(field)) {
            return decorateWebElement(loader, locator, elementName);
        } else if (isTypifiedElementList(field)) {
            @SuppressWarnings("unchecked")
            Class<TypifiedElement> typifiedElementClass = (Class<TypifiedElement>) getGenericParameterClass(field);
            return decorateTypifiedElementList(typifiedElementClass, loader, locator, elementName);
        } else if (isHtmlElementList(field)) {
            @SuppressWarnings("unchecked")
            Class<HtmlElement> htmlElementClass = (Class<HtmlElement>) getGenericParameterClass(field);
            return decorateHtmlElementList(htmlElementClass, loader, locator, elementName);
        } else if (isWebElementList(field)) {
            return decorateWebElementList(loader, locator, elementName);
        }

        return null;
    }

    private boolean isDecoratableField(Field field) {
        // TODO Protecting wrapped element from initialization basing on its name is unsafe. Think of a better way.
        if (isWebElement(field) && !field.getName().equals("wrappedElement")) {
            return true;
        }

        return (isWebElementList(field) || isHtmlElement(field) || isHtmlElementList(field) ||
                isTypifiedElement(field) || isTypifiedElementList(field));
    }

    private <T extends TypifiedElement> T decorateTypifiedElement(Class<T> elementClass, ClassLoader loader,
                                                                  ElementLocator locator, String elementName) {
        // Create typified element and initialize it with WebElement proxy
        WebElement elementToWrap = HtmlElementFactory.createNamedProxyForWebElement(loader, locator, elementName);
        T typifiedElementInstance = HtmlElementFactory.createTypifiedElementInstance(elementClass, elementToWrap);
        typifiedElementInstance.setName(elementName);
        return typifiedElementInstance;
    }

    private <T extends HtmlElement> T decorateHtmlElement(Class<T> elementClass, ClassLoader loader,
                                                          ElementLocator locator, String elementName) {
        // Create block and initialize it with WebElement proxy
        WebElement elementToWrap = HtmlElementFactory.createNamedProxyForWebElement(loader, locator, elementName);
        T htmlElementInstance = HtmlElementFactory.createHtmlElementInstance(elementClass);
        htmlElementInstance.setWrappedElement(elementToWrap);
        htmlElementInstance.setName(elementName);
        // Recursively initialize elements of the block
        PageFactory.initElements(new HtmlElementDecorator(elementToWrap), htmlElementInstance);
        return htmlElementInstance;
    }

    private WebElement decorateWebElement(ClassLoader loader, ElementLocator locator, String elementName) {
        return HtmlElementFactory.createNamedProxyForWebElement(loader, locator, elementName);
    }

    private <T extends TypifiedElement> List<T> decorateTypifiedElementList(Class<T> elementClass, ClassLoader loader,
                                                                            ElementLocator locator, String listName) {
        return HtmlElementFactory.createNamedProxyForTypifiedElementList(elementClass, loader, locator, listName);
    }

    private <T extends HtmlElement> List<T> decorateHtmlElementList(Class<T> elementClass, ClassLoader loader,
                                                                    ElementLocator locator, String listName) {
        return HtmlElementFactory.createNamedProxyForHtmlElementList(elementClass, loader, locator, listName);
    }

    private List<WebElement> decorateWebElementList(ClassLoader loader, ElementLocator locator, String listName) {
        return HtmlElementFactory.createNamedProxyForWebElementList(loader, locator, listName);
    }
}
