package ru.yandex.qatools.htmlelements.loader;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

import static ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementUtils.getElementName;
import static ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementUtils.isHtmlElement;

/**
 * Contains methods for blocks of elements initialization and page objects initialization.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class HtmlElementLoader {
    /**
     * Creates and initializes a block of elements if the given class is {@link HtmlElement} or its successor
     * and initializes page object otherwise.
     *
     * @param clazz  A class to be instantiated and initialized.
     * @param driver The {@code WebDriver} instance that will be used to look up the elements.
     * @return Initialized instance of the specified class.
     * @see #createHtmlElement(Class, SearchContext)
     * @see #createPageObject(Class, WebDriver)
     */
    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> clazz, WebDriver driver) {
        if (isHtmlElement(clazz)) {
            return (T) createHtmlElement((Class<HtmlElement>) clazz, driver);
        } else {
            return createPageObject(clazz, driver);
        }
    }

    /**
     * Initializes {@code instance} as a block of elements it is instance of {@link HtmlElement}
     * or its successor and as a page object otherwise.
     *
     * @param instance Object to be initialized.
     * @param driver   The {@code WebDriver} instance that will be used to look up the elements.
     * @see #populateHtmlElement(HtmlElement, SearchContext)
     * @see #createPageObject(Class, WebDriver)
     */
    public static <T> void populate(T instance, WebDriver driver) {
        if (isHtmlElement(instance)) {
            HtmlElement htmlElement = (HtmlElement) instance;
            populateHtmlElement(htmlElement, driver);
        } else {
            // Otherwise consider instance as a page object
            populatePageObject(instance, driver);
        }
    }

    /**
     * Creates an instance of the given class representing a block of elements and initializes its fields
     * with lazy proxies.
     * <p/>
     * Processes {@link ru.yandex.qatools.htmlelements.annotations.Block} annotation of the given class
     * to set the way of locating the block itself and {@link org.openqa.selenium.support.FindBy} and
     * {@link org.openqa.selenium.support.FindBys} annotations of its fields for setting the way
     * of locating elements of the block.
     * <p/>
     * Fields to be proxied:
     * <p/>
     * <ul>
     * <li>{@code WebElement}</li>
     * <li>List of {@code WebElements}</li>
     * <li>Block of elements ({@link HtmlElement} and its successors)</li>
     * <li>List of blocks</li>
     * <li>Typified element ({@link ru.yandex.qatools.htmlelements.element.TypifiedElement} successors)</li>
     * <li>List of typified elements</li>
     * </ul>
     *
     * @param clazz         A class to be instantiated and initialized.
     * @param searchContext {@code SearchContext} that will be used to look up the elements.
     * @return Initialized instance of the specified class.
     */
    public static <T extends HtmlElement> T createHtmlElement(Class<T> clazz, SearchContext searchContext) {
        T htmlElementInstance = HtmlElementFactory.createHtmlElementInstance(clazz);
        populateHtmlElement(htmlElementInstance, new HtmlElementLocatorFactory(searchContext));
        return htmlElementInstance;
    }

    /**
     * Creates an instance of the given page object class and initializes its fields with lazy proxies.
     * <p/>
     * Processes {@link org.openqa.selenium.support.FindBy} and {@link org.openqa.selenium.support.FindBys}
     * annotations of the fields for setting the way of locating them.
     * <p/>
     * Fields to be proxied:
     * <p/>
     * <ul>
     * <li>{@code WebElement}</li>
     * <li>List of {@code WebElements}</li>
     * <li>Block of elements ({@link HtmlElement} and its successors)</li>
     * <li>List of blocks</li>
     * <li>Typified element ({@link ru.yandex.qatools.htmlelements.element.TypifiedElement} successors)</li>
     * <li>List of typified elements</li>
     * </ul>
     *
     * @param clazz  A class to be instantiated and initialized.
     * @param driver The {@code WebDriver} instance that will be used to look up the elements.
     * @return Initialized instance of the specified class.
     */
    public static <T> T createPageObject(Class<T> clazz, WebDriver driver) {
        T page = HtmlElementFactory.createPageObjectInstance(clazz, driver);
        populatePageObject(page, new HtmlElementLocatorFactory(driver));
        return page;
    }

    /**
     * Initializes fields of the given block of elements with lazy proxies.
     * <p/>
     * Processes {@link ru.yandex.qatools.htmlelements.annotations.Block} annotation of the class
     * of the given block to set the way of locating the block itself and {@link org.openqa.selenium.support.FindBy}
     * and {@link org.openqa.selenium.support.FindBys} annotations of its fields for setting the way
     * of locating elements of the block.
     * <p/>
     * Fields to be proxied:
     * <p/>
     * <ul>
     * <li>{@code WebElement}</li>
     * <li>List of {@code WebElements}</li>
     * <li>Block of elements ({@link HtmlElement} and its successors)</li>
     * <li>List of blocks</li>
     * <li>Typified element ({@link ru.yandex.qatools.htmlelements.element.TypifiedElement} successors)</li>
     * <li>List of typified elements</li>
     * </ul>
     *
     * @param htmlElement   Block of elements to be initialized.
     * @param searchContext {@code SearchContext} that will be used to look up the elements.
     */
    public static void populateHtmlElement(HtmlElement htmlElement, SearchContext searchContext) {
        populateHtmlElement(htmlElement, new HtmlElementLocatorFactory(searchContext));
    }

    /**
     * Initializes fields of the given block of elements using specified locator factory.
     *
     * @param htmlElement    Block of elements to be initialized.
     * @param locatorFactory Locator factory that will be used to locate block elements.
     */
    public static void populateHtmlElement(HtmlElement htmlElement, CustomElementLocatorFactory locatorFactory) {
        @SuppressWarnings("unchecked")
        Class<HtmlElement> htmlElementClass = (Class<HtmlElement>) htmlElement.getClass();
        // Create locator that will handle Block annotation
        ElementLocator locator = locatorFactory.createLocator(htmlElementClass);
        ClassLoader htmlElementClassLoader = htmlElement.getClass().getClassLoader();
        // Initialize block with WebElement proxy and set its name
        WebElement elementToWrap = HtmlElementFactory.createProxyForWebElement(htmlElementClassLoader, locator);
        htmlElement.setWrappedElement(elementToWrap);
        String elementName = getElementName(htmlElementClass);
        htmlElement.setName(elementName);
        // Initialize elements of the block
        PageFactory.initElements(new HtmlElementDecorator(elementToWrap), htmlElement);
    }

    /**
     * Initializes fields of the given page object with lazy proxies.
     * <p/>
     * Processes {@link org.openqa.selenium.support.FindBy} and {@link org.openqa.selenium.support.FindBys}
     * annotations of the fields for setting the way of locating them.
     * <p/>
     * Fields to be proxied:
     * <p/>
     * <ul>
     * <li>{@code WebElement}</li>
     * <li>List of {@code WebElements}</li>
     * <li>Block of elements ({@link HtmlElement} and its successors)</li>
     * <li>List of blocks</li>
     * <li>Typified element ({@link ru.yandex.qatools.htmlelements.element.TypifiedElement} successors)</li>
     * <li>List of typified elements</li>
     * </ul>
     *
     * @param page   Page object to be initialized.
     * @param driver The {@code WebDriver} instance that will be used to look up the elements.
     */
    public static void populatePageObject(Object page, WebDriver driver) {
        populatePageObject(page, new HtmlElementLocatorFactory(driver));
    }

    /**
     * Initializes fields of the given page object using specified locator factory.
     *
     * @param page           Page object to be initialized.
     * @param locatorFactory Locator factory that will be used to locate elements.
     */
    public static void populatePageObject(Object page, CustomElementLocatorFactory locatorFactory) {
        PageFactory.initElements(new HtmlElementDecorator(locatorFactory), page);
    }
}
