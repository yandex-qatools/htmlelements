package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Represents web page table element. Provides convenient ways of retrieving data stored in it.
 *
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

    /**
     * Returns a list of table heading elements ({@code <th>}).
     *
     * Multiple rows of heading elements, ({@code <tr>}), are flattened
     * i.e. the second row, ({@code <tr>}), will follow the first, which can be
     * misleading when the table uses {@code colspan} and {@code rowspan}.
     *
     * @return List with table heading elements.
     */
    public List<WebElement> getHeadings() {
        return getWrappedElement().findElements(By.xpath(".//th"));
    }

    /**
     * Returns text values of table heading elements (contained in "th" tags).
     *
     * @return List with text values of table heading elements.
     */
    public List<String> getHeadingsAsString() {
        return getHeadings().stream()
                .map(WebElement::getText)
                .collect(toList());
    }

    /**
     * Returns table cell elements ({@code <td>}), grouped by rows.
     *
     * @return List where each item is a table row.
     */
    public List<List<WebElement>> getRows() {
        return getWrappedElement()
                .findElements(By.xpath(".//tr"))
                .stream()
                .map(rowElement -> rowElement.findElements(By.xpath(".//td")))
                .filter(row -> row.size() > 0) // ignore rows with no <td> tags
                .collect(toList());
    }

    /**
     * Returns text values of table cell elements ({@code <td>}), grouped by rows.
     *
     * @return List where each item is text values of a table row.
     */
    public List<List<String>> getRowsAsString() {
        return getRows().stream()
                .map(row -> row.stream()
                        .map(WebElement::getText)
                        .collect(toList()))
                .collect(toList());
    }

    /**
     * Returns table cell elements ({@code <td>}), grouped by columns.
     *
     * @return List where each item is a table column.
     */
    public List<List<WebElement>> getColumns() {
        List<List<WebElement>> columns = new ArrayList<>();
        List<List<WebElement>> rows = getRows();

        if (rows.isEmpty()) {
            return columns;
        }

        int columnCount = rows.get(0).size();
        for (int i = 0; i < columnCount; i++) {
            List<WebElement> column = new ArrayList<>();
            for (List<WebElement> row : rows) {
                column.add(row.get(i));
            }
            columns.add(column);
        }

        return columns;
    }

    /**
     * Returns table cell elements ({@code <td>}), of a particular column.
     *
     * @param index the 1-based index of the desired column
     * @return List where each item is a cell of a particular column.
     */
    public List<WebElement> getColumnByIndex(int index) {
        return getWrappedElement().findElements(
                        By.cssSelector(String.format("tr > td:nth-of-type(%d)", index)));
    }

    /**
     * Returns text values of table cell elements ({@code <td>}), grouped by columns.
     *
     * @return List where each item is text values of a table column.
     */
    public List<List<String>> getColumnsAsString() {
        return getColumns().stream()
                .map(row -> row.stream()
                        .map(WebElement::getText)
                        .collect(toList()))
                .collect(toList());
    }

    /**
     * Returns table cell element ({@code <td>}), at i-th row and j-th column.
     *
     * @param i Row number
     * @param j Column number
     * @return Cell element at i-th row and j-th column.
     */
    public WebElement getCellAt(int i, int j) {
        return getRows().get(i).get(j);
    }

    /**
     * Returns list of maps where keys are table headings and values are table row elements ({@code <td>}).
     */
    public List<Map<String, WebElement>> getRowsMappedToHeadings() {
        return getRowsMappedToHeadings(getHeadingsAsString());
    }

    /**
     * Returns list of maps where keys are passed headings and values are table row elements ({@code <td>}),.
     *
     * @param headings List containing strings to be used as table headings.
     */
    public List<Map<String, WebElement>> getRowsMappedToHeadings(List<String> headings) {
        List<List<WebElement>> rows = getRows();
        return rows.stream()
                .map(row -> row.stream()
                        .collect(toMap(e -> headings.get(row.indexOf(e)), identity())))
                .collect(toList());
    }

    /**
     * Same as {@link #getRowsMappedToHeadings()} but retrieves text from row elements ({@code <td>}).
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings() {
        return getRowsAsStringMappedToHeadings(getHeadingsAsString());
    }

    /**
     * Same as {@link #getRowsMappedToHeadings(java.util.List)} but retrieves text from row elements ({@code <td>}).
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings(List<String> headings) {
        return getRowsMappedToHeadings(headings).stream()
                .map(m -> m.entrySet().stream()
                        .collect(toMap(Map.Entry::getKey, e -> e.getValue().getText())))
                .collect(toList());
    }
}
