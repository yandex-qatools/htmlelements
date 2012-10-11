package ru.yandex.qatools.htmlelements.loader.decorator;

import java.lang.reflect.Field;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandler;
import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandlerFactory;

public class HtmlElementAnnotationsHandlerFactory<T extends HtmlElement> implements AnnotationsHandlerFactory<T> {

	@Override
	public AnnotationsHandler getFieldAnnotationsHandler(Field field) {
		return new HtmlElementFieldAnnotationsHandler(field);
	}

	@Override
	public AnnotationsHandler getClassAnnotationsHandler(Class<T> clazz) {
		return new HtmlElementClassAnnotationsHandler<>(clazz);
	}

}
