package ru.yandex.qatools.htmlelements.element;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.exceptions.HtmlElementsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.convert;
import static ch.lambdaj.Lambda.convertMap;
import static ru.yandex.qatools.htmlelements.element.Table.ListConverter.toListsConvertingEachItem;
import static ru.yandex.qatools.htmlelements.element.Table.MapConverter.toMapsConvertingEachValue;
import static ru.yandex.qatools.htmlelements.element.Table.WebElementToTextConverter.toText;
import static ru.yandex.qatools.htmlelements.element.Table.WebElementToTextConverter.toTextValues;

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
     * Returns table heading elements (contained in "th" tags).
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
        return convert(getHeadings(), toTextValues());
    }

    /**
     * Returns table cell elements grouped by rows.
     *
     * @return List where each item is a table row.
     */
    public List<List<WebElement>> getRows() {
        List<List<WebElement>> rows = new ArrayList<List<WebElement>>();
        List<WebElement> rowElements = getWrappedElement().findElements(By.xpath(".//tr"));
        for (WebElement rowElement : rowElements) {
            rows.add(rowElement.findElements(By.xpath(".//td")));
        }
        return rows;
    }

    /**
     * Returns text values of table cell elements grouped by rows.
     *
     * @return List where each item is text values of a table row.
     */
    public List<List<String>> getRowsAsString() {
        return convert(getRows(), toListsConvertingEachItem(toTextValues()));
    }

    /**
     * Returns table cell elements grouped by columns.
     *
     * @return List where each item is a table column.
     */
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

    /**
     * Returns text values of table cell elements grouped by columns.
     *
     * @return List where each item is text values of a table column.
     */
    public List<List<String>> getColumnsAsString() {
        return convert(getColumns(), toListsConvertingEachItem(toTextValues()));
    }

    /**
     * Returns table cell element at i-th row and j-th column.
     *
     * @param i Row number
     * @param j Column number
     * @return Cell element at i-th row and j-th column.
     */
    public WebElement getCellAt(int i, int j) {
        return getRows().get(i).get(j);
    }

    /**
     * Returns list of maps where keys are table headings and values are table row elements.
     */
    public List<Map<String, WebElement>> getRowsMappedToHeadings() {
        return getRowsMappedToHeadings(getHeadingsAsString());
    }

    /**
     * Returns list of maps where keys are passed headings and values are table row elements.
     * @param headings List containing strings to be used as table headings.
     */
    public List<Map<String, WebElement>> getRowsMappedToHeadings(List<String> headings) {
        List<Map<String, WebElement>> rowsMappedToHeadings = new ArrayList<Map<String, WebElement>>();
        List<List<WebElement>> rows = getRows();

        if (rows.isEmpty()) {
            return rowsMappedToHeadings;
        }

        for (List<WebElement> row : rows) {
            if (row.size() != headings.size()) {
                throw new HtmlElementsException("Headings count is not equal to number of cells in row");
            }

            Map<String, WebElement> rowToHeadingsMap = new HashMap<String, WebElement>();
            int cellNumber = 0;
            for (String heading : headings) {
                rowToHeadingsMap.put(heading, row.get(cellNumber));
                cellNumber++;
            }
            rowsMappedToHeadings.add(rowToHeadingsMap);
        }

        return rowsMappedToHeadings;
    }

    /**
     * Same as {@link #getRowsMappedToHeadings()} but retrieves text from row elements.
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings() {
        return getRowsAsStringMappedToHeadings(getHeadingsAsString());
    }

    /**
     * Same as {@link #getRowsMappedToHeadings(java.util.List)} but retrieves text from row elements.
     */
    public List<Map<String, String>> getRowsAsStringMappedToHeadings(List<String> headings) {
        return convert(getRowsMappedToHeadings(headings), toMapsConvertingEachValue(toText()));
    }


    /* Inner utility converters */

    /**
     * Converts {@link WebElement} to text contained in it
     */
    static class WebElementToTextConverter implements Converter<WebElement, String> {

        public static Converter<WebElement, String> toText() {
            return new WebElementToTextConverter();
        }

        public static Converter<WebElement, String> toTextValues() {
            return new WebElementToTextConverter();
        }

        private WebElementToTextConverter() {
        }

        @Override
        public String convert(WebElement element) {
            return element.getText();
        }
    }

    /**
     * Converts {@code List&lt;F&gt;} to {@code List&lt;T&gt;} by applying specified converter to each list element.
     */
    static class ListConverter<F, T> implements Converter<List<F>, List<T>> {
        private final Converter<F, T> itemsConverter;

        public static <F, T> Converter<List<F>, List<T>> toListsConvertingEachItem(Converter<F, T> itemsConverter) {
            return new ListConverter<F, T>(itemsConverter);
        }

        private ListConverter(Converter<F, T> itemsConverter) {
            this.itemsConverter = itemsConverter;
        }

        @Override
        public List<T> convert(List<F> list) {
            return Lambda.convert(list, itemsConverter);
        }
    }

    /**
     * Converts {@code Map&lt;K, F&gt;} to {@code Map&lt;K, T&gt;} by applying specified converter to each value
     * in a map.
     */
    static class MapConverter<K, F, T> implements Converter<Map<K, F>, Map<K, T>> {
        private final Converter<F, T> valueConverter;

        public static <F, T> Converter<Map<String, F>, Map<String, T>> toMapsConvertingEachValue(Converter<F, T> valueConverter) {
            return new MapConverter<String, F, T>(valueConverter);
        }

        private MapConverter(Converter<F, T> valueConverter) {
            this.valueConverter = valueConverter;
        }

        @Override
        public Map<K, T> convert(Map<K, F> map) {
            return convertMap(map, valueConverter);
        }
    }
}
