package ru.yandex.qatools.htmlelements.loader.decorator;

import java.lang.reflect.Field;

import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandler;
import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandlerFactory;

public class HtmlElementAnnotationsHandlerFactory implements AnnotationsHandlerFactory {

	@Override
	public AnnotationsHandler getAnnotationsHandler(Field field) {
		return new HtmlElementFieldAnnotationsHandler(field);
	}

	@Override
	public AnnotationsHandler getAnnotationsHandler(Class clazz) {
		return new HtmlElementClassAnnotationsHandler(clazz);
	}

}
