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
import static ru.yandex.qatools.htmlelements.element.Table.ListConverter.toListConvertingEachItem;
import static ru.yandex.qatools.htmlelements.element.Table.MapConverter.toMapConvertingEachValue;
import static ru.yandex.qatools.htmlelements.element.Table.WebElementToTextConverter.toText;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 11.03.13
 */
// TODO Add JavaDoc
public class Table extends TypifiedElement {
    /**
     * Specifies {@link org.openqa.selenium.WebElement} representing table tag.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public Table(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public List<WebElement> getHeadings() {
        return getWrappedElement().findElements(By.xpath(".//th"));
    }

    public List<String> getHeadingsAsString() {
        return convert(getHeadings(), toText());
    }

    public List<List<WebElement>> getRows() {
        List<List<WebElement>> rows = new ArrayList<List<WebElement>>();
        List<WebElement> rowElements = getWrappedElement().findElements(By.xpath(".//tr"));
        for (WebElement rowElement : rowElements) {
            rows.add(rowElement.findElements(By.xpath(".//td")));
        }
        return rows;
    }

    public List<List<String>> getRowsAsString() {
        return convert(getRows(), toListConvertingEachItem(toText()));
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

    public List<List<String>> getColumnsAsString() {
        return convert(getColumns(), toListConvertingEachItem(toText()));
    }

    public WebElement getCellAt(int i, int j) {
        return getRows().get(i).get(j);
    }

    public List<Map<String, WebElement>> getRowsMappedToHeadings() {
        return getRowsMappedToHeadings(getHeadingsAsString());
    }

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

    public List<Map<String, String>> getRowsAsStringMappedToHeadings() {
        return getRowsAsStringMappedToHeadings(getHeadingsAsString());
    }

    public List<Map<String, String>> getRowsAsStringMappedToHeadings(List<String> headings) {
        return convert(getRowsMappedToHeadings(headings), toMapConvertingEachValue(toText()));
    }


    /* Inner utility converters */

    /**
     * Converts {@link WebElement} to text contained in it
     */
    static class WebElementToTextConverter implements Converter<WebElement, String> {

        public static Converter<WebElement, String> toText() {
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

        public static <F, T> Converter<List<F>, List<T>> toListConvertingEachItem(Converter<F, T> itemsConverter) {
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

        public static <F, T> Converter<Map<String, F>, Map<String, T>> toMapConvertingEachValue(Converter<F, T> valueConverter) {
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
