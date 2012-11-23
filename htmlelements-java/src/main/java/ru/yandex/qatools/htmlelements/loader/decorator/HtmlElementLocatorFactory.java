package ru.yandex.qatools.htmlelements.loader.decorator;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import ru.yandex.qatools.htmlelements.pagefactory.AjaxElementLocator;
import ru.yandex.qatools.htmlelements.pagefactory.CustomElementLocatorFactory;

/**
 * A factory for producing locator instances.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 15.08.12
 */
public class HtmlElementLocatorFactory extends CustomElementLocatorFactory {
    private final SearchContext searchContext;

    public HtmlElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    /**
     * Creates locator for the given field. Created locator will process {@link org.openqa.selenium.support.FindBy},
     * {@link org.openqa.selenium.support.FindBys}, {@link ru.yandex.qatools.htmlelements.annotations.Block} and
     * {@link org.openqa.selenium.support.CacheLookup} annotations.
     * @param field Field for which locator will be created.
     */
    @Override
    public ElementLocator createLocator(Field field) {
    	return new AjaxElementLocator(searchContext, new HtmlElementFieldAnnotationsHandler(field));
    }
    
    /**
     * Creates locator for the given field. Created locator will process {@link org.openqa.selenium.support.FindBy},
     * {@link org.openqa.selenium.support.FindBys}, {@link ru.yandex.qatools.htmlelements.annotations.Block} and
     * {@link org.openqa.selenium.support.CacheLookup} annotations.
     * @param clazz Class for which locator will be created.
     */
    public ElementLocator createLocator(Class clazz) {
		return new AjaxElementLocator(searchContext, new HtmlElementClassAnnotationsHandler(clazz)); 
    }
}
