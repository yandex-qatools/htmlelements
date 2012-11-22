package ru.yandex.qatools.htmlelements.loader.decorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import ru.yandex.qatools.htmlelements.pagefactory.AjaxElementLocator;
import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandler;
import ru.yandex.qatools.htmlelements.pagefactory.DefaultElementLocator;

/**
 * A factory for producing locator instances.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 15.08.12
 */
public class HtmlElementLocatorFactory implements ElementLocatorFactory {
    private final SearchContext searchContext;
    private final Class<? extends DefaultElementLocator> elementLocatorClass;

    public HtmlElementLocatorFactory(SearchContext searchContext) {
        this(searchContext, AjaxElementLocator.class);
    }
    
    public HtmlElementLocatorFactory(SearchContext context, Class<? extends DefaultElementLocator> elementLocatorClass) {
    	this.searchContext = context;
    	this.elementLocatorClass = elementLocatorClass;
    }

    /**
     * Creates locator for the given field. Created locator will process {@link org.openqa.selenium.support.FindBy},
     * {@link org.openqa.selenium.support.FindBys}, {@link ru.yandex.qatools.htmlelements.annotations.Block} and
     * {@link org.openqa.selenium.support.CacheLookup} annotations.
     * @param field Field for which locator will be created.
     */
    @Override
    public ElementLocator createLocator(Field field) {
    	try {
			Constructor<? extends DefaultElementLocator> ctor = 
					elementLocatorClass.getConstructor(SearchContext.class, AnnotationsHandler.class);
			return ctor.newInstance(searchContext, new HtmlElementFieldAnnotationsHandler(field)); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Creates locator for the given field. Created locator will process {@link org.openqa.selenium.support.FindBy},
     * {@link org.openqa.selenium.support.FindBys}, {@link ru.yandex.qatools.htmlelements.annotations.Block} and
     * {@link org.openqa.selenium.support.CacheLookup} annotations.
     * @param clazz Class for which locator will be created.
     */
    public ElementLocator createLocator(Class clazz) {
    	try {
			Constructor<? extends DefaultElementLocator> ctor = 
					elementLocatorClass.getConstructor(SearchContext.class, AnnotationsHandler.class);
			return ctor.newInstance(searchContext, new HtmlElementClassAnnotationsHandler(clazz)); 
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
    }
}
