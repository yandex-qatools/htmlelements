package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.03.13
 */
public class Table extends TypifiedElement {
    /**
     * Specifies {@link org.openqa.selenium.WebElement} representing table tag.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public Table(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public List<List<WebElement>> getRows() {
        List<List<WebElement>> elements = new ArrayList<List<WebElement>>();
        List<WebElement> rowElements = getWrappedElement().findElements(By.xpath(".//tr"));
        for (WebElement rowElement : rowElements) {
            elements.add(rowElement.findElements(By.xpath(".//td")));
        }

        return elements;
    }

}
