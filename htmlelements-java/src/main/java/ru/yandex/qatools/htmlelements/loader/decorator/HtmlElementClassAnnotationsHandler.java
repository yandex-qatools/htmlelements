package ru.yandex.qatools.htmlelements.loader.decorator;

import org.openqa.selenium.By;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;
import ru.yandex.qatools.htmlelements.pagefactory.AnnotationsHandler;

/**
 * Handles {@link Block} annotation of {@link HtmlElement} and its successors.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 20.08.12
 */
public class HtmlElementClassAnnotationsHandler<T extends HtmlElement> extends AnnotationsHandler {
    Class<T> htmlElementClass;

    public HtmlElementClassAnnotationsHandler(Class<T> htmlElementClass) {
        this.htmlElementClass = htmlElementClass;
    }

    @Override
    public By buildBy() {
        if (!htmlElementClass.isAnnotationPresent(Block.class)) {
            throw new HtmlElementsException("Unable to initialize element, Block annotation is not present");
        }
        Block block = htmlElementClass.getAnnotation(Block.class);
        return buildByFromFindBy(block.value());
    }

    @Override
    public boolean shouldCache() {
        return false;
    }
}
