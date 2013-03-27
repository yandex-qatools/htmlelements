package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.convert;
import static ru.yandex.qatools.htmlelements.utils.WebElementToTextConverter.toText;

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

    public List<String> getHeadings() {
        List<WebElement> headingElements = getWrappedElement().findElements(By.xpath(".//th"));
        return convert(headingElements, toText());
    }

    public List<List<WebElement>> getRows() {
        List<List<WebElement>> rows = new ArrayList<List<WebElement>>();
        List<WebElement> rowElements = getWrappedElement().findElements(By.xpath(".//tr"));
        for (WebElement rowElement : rowElements) {
            rows.add(rowElement.findElements(By.xpath(".//td")));
        }
        return rows;
    }

    public List<List<WebElement>> getColumns() {
        List<List<WebElement>> columns = new ArrayList<List<WebElement>>();
        List<List<WebElement>> rows = getRows();
        if (rows.isEmpty()) {
            return columns;
        }

        int columnsNumber = rows.get(0).size();
        for (int i = 0; i < columnsNumber; i++) {
            List<WebElement> column = new ArrayList<WebElement>();
            for (List<WebElement> row : rows) {
                column.add(row.get(i));
            }
            columns.add(column);
        }

        return columns;
    }
}
