package ru.yandex.qatools.htmlelements.pagefactory;

import java.lang.reflect.Field;

public interface AnnotationsHandlerFactory {
	public AnnotationsHandler getAnnotationsHandler(Field field);
	public AnnotationsHandler getAnnotationsHandler(Class clazz);
}
