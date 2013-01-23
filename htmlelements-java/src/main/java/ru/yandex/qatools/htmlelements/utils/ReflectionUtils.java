package ru.yandex.qatools.htmlelements.utils;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

/**
 * User: eroshenkoam
 * Date: 1/22/13, 5:53 PM
 */
public class ReflectionUtils {

    public static <T> T newInstance(Class<T> clazz, Object... args) throws IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers())) {
            Class outerClass = clazz.getDeclaringClass();
            Object outerObject = outerClass.newInstance();
            return invokeConstructor(clazz, Lists.asList(outerObject, args).toArray());
        } else {
            return invokeConstructor(clazz, args);
        }
    }
}
