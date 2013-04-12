package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.pagefactory.DefaultFieldAnnotationsHandler;

import java.lang.reflect.Field;

import static ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementUtils.*;

/**
 * Extends default field annotations handling mechanism with processing
 * {@link Block} annotation for blocks and lists of blocks.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 15.08.12
 */
public class HtmlElementFieldAnnotationsHandler extends DefaultFieldAnnotationsHandler {
    public HtmlElementFieldAnnotationsHandler(Field field) {
        super(field);
    }

    @Override
    public By buildBy() {
        if (isHtmlElement(getField())) {
            return buildByFromHtmlElementAnnotations();
        }
        if (isHtmlElementList(getField())) {
            return buildByFromHtmlElementListAnnotations();
        } else {
            return buildByFromDefaultAnnotations();
        }
    }

    private By buildByFromHtmlElementAnnotations() {
        assertValidAnnotations();

        if (getField().isAnnotationPresent(FindBys.class)) {
            FindBys findBys = getField().getAnnotation(FindBys.class);
            return buildByFromFindBys(findBys);
        }

        if (getField().isAnnotationPresent(FindBy.class)) {
            FindBy findBy = getField().getAnnotation(FindBy.class);
            return buildByFromFindBy(findBy);
        }

        Class<?> fieldClass = getField().getType();
        while (fieldClass != Object.class) {
            if (fieldClass.isAnnotationPresent(Block.class)) {
                Block block = fieldClass.getAnnotation(Block.class);
                FindBy findBy = block.value();
                return buildByFromFindBy(findBy);
            }
            fieldClass = fieldClass.getSuperclass();
        }

        return buildByFromDefault();
    }

    private By buildByFromHtmlElementListAnnotations() {
        assertValidAnnotations();

        if (getField().isAnnotationPresent(FindBys.class)) {
            FindBys findBys = getField().getAnnotation(FindBys.class);
            return buildByFromFindBys(findBys);
        }

        if (getField().isAnnotationPresent(FindBy.class)) {
            FindBy findBy = getField().getAnnotation(FindBy.class);
            return buildByFromFindBy(findBy);
        }

        Class<?> listParameterClass = getGenericParameterClass(getField());
        while (listParameterClass != Object.class) {
            if (listParameterClass.isAnnotationPresent(Block.class)) {
                Block block = listParameterClass.getAnnotation(Block.class);
                FindBy findBy = block.value();
                return buildByFromFindBy(findBy);
            }
            listParameterClass = listParameterClass.getSuperclass();
        }

        throw new HtmlElementsException(String.format("Cannot determine how to locate element %s", getField()));
    }

    private By buildByFromDefaultAnnotations() {
        return super.buildBy();
    }
}
