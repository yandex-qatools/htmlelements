package ru.yandex.qatools.htmlelements.matchers.testelements;

import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
@Block(@FindBy(className = CustomBlock.CLASS_NAME))
public class CustomBlock extends HtmlElement {
    public static final String CLASS_NAME = "custom_block";
}
