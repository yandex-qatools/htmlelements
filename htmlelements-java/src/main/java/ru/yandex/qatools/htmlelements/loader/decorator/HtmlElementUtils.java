package ru.yandex.qatools.htmlelements.loader.decorator;

import org.apache.commons.lang.WordUtils;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Contains utility methods that are used by initialization mechanism.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 21.08.12
 */
public class HtmlElementUtils {
    public static boolean isHtmlElement(Field field) {
        return isHtmlElement(field.getType());
    }

    public static boolean isHtmlElement(Class<?> clazz) {
        return HtmlElement.class.isAssignableFrom(clazz);
    }

    public static <T> boolean isHtmlElement(T instance) {
        return HtmlElement.class.isInstance(instance);
    }

    public static boolean isTypifiedElement(Field field) {
        return isTypifiedElement(field.getType());
    }

    public static boolean isTypifiedElement(Class<?> clazz) {
        return TypifiedElement.class.isAssignableFrom(clazz);
    }

    public static boolean isWebElement(Field field) {
        return isWebElement(field.getType());
    }

    public static boolean isWebElement(Class<?> clazz) {
        return WebElement.class.isAssignableFrom(clazz);
    }

    public static boolean isHtmlElementList(Field field) {
        if (!isParameterizedList(field)) {
            return false;
        }
        Class listParameterClass = getGenericParameterClass(field);
        return isHtmlElement(listParameterClass);
    }

    public static boolean isTypifiedElementList(Field field) {
        if (!isParameterizedList(field)) {
            return false;
        }
        Class listParameterClass = getGenericParameterClass(field);
        return isTypifiedElement(listParameterClass);
    }

    public static boolean isWebElementList(Field field) {
        if (!isParameterizedList(field)) {
            return false;
        }
        Class listParameterClass = getGenericParameterClass(field);
        return isWebElement(listParameterClass);
    }

    public static Class getGenericParameterClass(Field field) {
        if (!hasGenericParameter(field)) {
            return null;
        }
        Type genericType = field.getGenericType();
        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }

    private static boolean isParameterizedList(Field field) {
        return isList(field) && hasGenericParameter(field);
    }

    private static boolean isList(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    private static boolean hasGenericParameter(Field field) {
        return field.getGenericType() instanceof ParameterizedType;
    }

    public static String getElementName(Field field) {
        if (field.isAnnotationPresent(Name.class)) {
            return field.getAnnotation(Name.class).value();
        }
        if (field.getType().isAnnotationPresent(Name.class)) {
            return field.getType().getAnnotation(Name.class).value();
        } else {
            return splitCamelCase(field.getName());
        }
    }

    public static <T> String getElementName(Class<T> clazz) {
        if (clazz.isAnnotationPresent(Name.class)) {
            return clazz.getAnnotation(Name.class).value();
        } else {
            return splitCamelCase(clazz.getSimpleName());
        }
    }

    private static String splitCamelCase(String camel) {
        return WordUtils.capitalizeFully(camel.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        ));
    }
}
