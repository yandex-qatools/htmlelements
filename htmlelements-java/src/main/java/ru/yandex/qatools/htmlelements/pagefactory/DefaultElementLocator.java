package ru.yandex.qatools.htmlelements.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

/**
 * A patch for {@code WebDriver} {@link org.openqa.selenium.support.pagefactory.DefaultElementLocator} class.
 * <p/>
 * The need for creating it is that the original {@code WebDriver} source code provides no possibility for
 * using different ways of annotations handling in
 * {@link org.openqa.selenium.support.pagefactory.DefaultElementLocator}. This leads to code duplication.
 * <p/>
 * We need to use a little bit different way of annotations handling while keeping implemented in
 * {@link org.openqa.selenium.support.pagefactory.DefaultElementLocator} mechanism of locating elements.
 * So we've just changed the constructor of the original class.
 */
public class DefaultElementLocator implements ElementLocator {
    private final SearchContext searchContext;
    private final boolean shouldCache;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;

    /**
     * Creates a new element locator.
     *
     * @param searchContext The context to use when finding the element
     * @param annotationsHandler The annotations handler for locating element
     */
    public DefaultElementLocator(SearchContext searchContext, AnnotationsHandler annotationsHandler) {
        this.searchContext = searchContext;
        this.shouldCache = annotationsHandler.shouldCache();
        this.by = annotationsHandler.buildBy();
    }

    /**
     * Find the element.
     */
    public WebElement findElement() {
        if (cachedElement != null && shouldCache) {
            return cachedElement;
        }

        WebElement element = searchContext.findElement(by);
        if (shouldCache) {
            cachedElement = element;
        }

        return element;
    }

    /**
     * Find the element list.
     */
    public List<WebElement> findElements() {
        if (cachedElementList != null && shouldCache) {
            return cachedElementList;
        }

        List<WebElement> elements = searchContext.findElements(by);
        if (shouldCache) {
            cachedElementList = elements;
        }

        return elements;
    }
}