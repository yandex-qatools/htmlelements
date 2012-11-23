package ru.yandex.qatools.htmlelements.pagefactory;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementClassAnnotationsHandler;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementFieldAnnotationsHandler;

public class MyElementLocatorFactory extends CustomElementLocatorFactory {
	private final SearchContext searchContext;

	public MyElementLocatorFactory(SearchContext searchContext) {
		this.searchContext = searchContext;
	}
	
	public ElementLocator createLocator(Field field) {
		return new MyElementLocator(searchContext, new HtmlElementFieldAnnotationsHandler(field));
	}
	
	public ElementLocator createLocator(Class clazz) {
		return new MyElementLocator(searchContext, new HtmlElementClassAnnotationsHandler(clazz));
	}

}
