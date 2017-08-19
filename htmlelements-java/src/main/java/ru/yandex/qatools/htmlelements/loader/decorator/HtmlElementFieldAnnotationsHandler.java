package ru.yandex.qatools.htmlelements.loader.decorator;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.Annotations;

import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.*;

/**
 * Extends default field annotations handling mechanism with processing
 * annotation for blocks and lists of blocks.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru Date: 15.08.12
 */
public class HtmlElementFieldAnnotationsHandler extends Annotations {
    public HtmlElementFieldAnnotationsHandler(Field field) {
        super(field);
    }

    @Override
    public By buildBy() {
        if (isHtmlElement(getField()) || isTypifiedElement(getField())) {
            return buildByFromHtmlElementAnnotations();
        }
        if (isHtmlElementList(getField()) || isTypifiedElementList(getField())) {
            return buildByFromHtmlElementListAnnotations();
        }
        return super.buildBy();
    }

    private By buildByFromFindAnnotations() {
        if (getField().isAnnotationPresent(FindBys.class)) {
            FindBys findBys = getField().getAnnotation(FindBys.class);
            return new FindBys.FindByBuilder().buildIt(findBys, null);
        }

        if (getField().isAnnotationPresent(FindAll.class)) {
            FindAll findAll = getField().getAnnotation(FindAll.class);
            return new FindAll.FindByBuilder().buildIt(findAll, null);
        }

        if (getField().isAnnotationPresent(FindBy.class)) {
            FindBy findBy = getField().getAnnotation(FindBy.class);
            return new FindBy.FindByBuilder().buildIt(findBy, null);
        }
        return null;
    }

    private By buildByFromHtmlElementAnnotations() {
        assertValidAnnotations();

        By result = buildByFromFindAnnotations();
        if (result != null) {
            return result;
        }

        Class<?> fieldClass = getField().getType();
        while (fieldClass != Object.class) {
            if (fieldClass.isAnnotationPresent(FindBy.class)) {
                return new FindBy.FindByBuilder().buildIt(fieldClass.getAnnotation(FindBy.class), null);
            }
            fieldClass = fieldClass.getSuperclass();
        }

        return buildByFromDefault();
    }

    private By buildByFromHtmlElementListAnnotations() {
        assertValidAnnotations();

        By result = buildByFromFindAnnotations();
        if (result != null) {
            return result;
        }

        Class<?> listParameterClass = getGenericParameterClass(getField());
        while (listParameterClass != Object.class) {
            if (listParameterClass.isAnnotationPresent(FindBy.class)) {
                return new FindBy.FindByBuilder().buildIt(listParameterClass.getAnnotation(FindBy.class), null);
            }
            listParameterClass = listParameterClass.getSuperclass();
        }

        throw new HtmlElementsException(String.format("Cannot determine how to locate element %s", getField()));
    }
}
