package ru.yandex.qatools.htmlelements.pagefactory;

import java.lang.reflect.Field;

public interface AnnotationsHandlerFactory<T> {
	public AnnotationsHandler getFieldAnnotationsHandler(Field field);
	public AnnotationsHandler getClassAnnotationsHandler(Class<T> clazz);
}
