package ru.yandex.qatools.htmlelements.matchers.testelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

@Block(@FindBy(className = NotExistingBlock.CLASS_NAME))
public class NotExistingBlock extends HtmlElement {
    public static final String CLASS_NAME = "complex_block";

    private List<CustomBlock> subBlock;

    @FindBy(css = "some_selector")
    private HtmlElement notExisting;
}
