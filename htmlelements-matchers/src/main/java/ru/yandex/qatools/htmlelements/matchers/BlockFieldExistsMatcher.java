package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

import static ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementUtils.*;


/**
 * Checks that all declared elements of block are present on page
 *
 * @author Pavel Zorin pazone@yandex-team.ru
 */

class BlockFieldExistsMatcher extends TypeSafeMatcher<Class<? extends HtmlElement>> {

    public static final String NO_FIELD = "Field '%s' not found on page.";
    public static final String NO_BLOCK = "Block '%s' not found on page.";
    public static final String BLOCK_ABSENT = "@Block annotation is absent in class '%s'.";

    protected BlockFieldExistsMatcher(WebDriver webDriver) {
        super();
        this.webDriver = webDriver;
    }

    private WebDriver webDriver;
    private String desc;

    @Override
    public boolean matchesSafely(Class item) {
        return processEntity(null, item);
    }

    private boolean processEntity(@Nullable WebElement parent, Class<?> clazz) {
        if (clazz.isAnnotationPresent(Block.class)) {
            WebElement current;
            try {
                current = makeCurrent(parent, clazz);
            } catch (Exception e) {
                desc = String.format(NO_BLOCK, clazz.getSimpleName());
                return false;
            }

            for (Field field : clazz.getDeclaredFields()) {
                if (hasBlockAnnotation(field.getType())) {
                    if (!processEntity(current, field.getType())) {
                        return false;
                    }
                } else if (isSingleElement(field)) {
                    if (!processField(current, field)) {
                        return false;
                    }
                } else if (isHtmlElementList(field)) {
                    if (!processEntity(current, getGenericParameterClass(field))) {
                        return false;
                    }
                }
            }
        } else {
            desc = String.format(BLOCK_ABSENT, clazz.getSimpleName());
            return false;
        }
        return true;
    }

    private WebElement makeCurrent(WebElement parent, Class<?> clazz) {
        if (parent != null) {
            return parent.findElement(retrieveSelector(clazz));
        } else {
            return webDriver.findElement(retrieveSelector(clazz));
        }

    }

    private boolean processField(WebElement parent, Field field) {
        if (parent.findElements(new AnnotationHelper().buildBy(field.getAnnotation(FindBy.class))).isEmpty()) {
            desc = String.format(NO_FIELD, field.getName());
            return false;
        }

        return true;
    }

    private By retrieveSelector(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Block.class)) {
            return new AnnotationHelper().buildBy(clazz.getAnnotation(Block.class).value());
        } else if (clazz.isAnnotationPresent(FindBy.class)) {
            return new AnnotationHelper().buildBy(clazz.getAnnotation(FindBy.class));
        }
        return null;
    }

    private boolean isSingleElement(Field field) {
        return (hasFindByAnnotation(field) || hasFindBysAnnotation(field)) && !isHtmlElementList(field);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(desc);
    }

    @Factory
    public static Matcher<Class<? extends HtmlElement>> blockFieldsExists(WebDriver webDriver) {
        return new BlockFieldExistsMatcher(webDriver);
    }

    private class AnnotationHelper extends Annotations {
        public AnnotationHelper() {
            super(null);
        }

        public By buildBy(FindBy findBy) {
            return buildByFromFindBy(findBy);
        }
    }

}
