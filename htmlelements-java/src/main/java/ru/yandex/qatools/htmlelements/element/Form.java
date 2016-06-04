package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * Represents web page form tag.
 * Provides handy way of filling form with data and submitting it.
 *
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         3/14/13, 4:38 PM
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.03.13
 */
public class Form extends TypifiedElement {
    private static final String CHECKBOX_FIELD = "checkbox";
    private static final String RADIO_FIELD = "radio";
    private static final String SELECT_FIELD = "select";
    private static final String INPUT_FIELD = "input";
    private static final String FILE_FIELD = "file";

    /**
     * Specifies {@link org.openqa.selenium.WebElement} representing form tag.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public Form(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Fills form with data contained in passed map.
     * For each map entry if an input with a name coincident with entry key exists
     * it is filled with string representation of entry value.
     * If an input with such a name is not found the corresponding entry is skipped.
     *
     * @param data Map containing data to fill form inputs with.
     */
    public void fill(Map<String, Object> data) {
        data.entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(
                        findElementByKey(e.getKey()),
                        Objects.toString(e.getValue(), "")))
                .filter(e -> !isNull(e.getKey()))
                .forEach(e -> fillElement(e.getKey(), e.getValue()));
    }

    protected WebElement findElementByKey(String key) {
        List<WebElement> elements = getWrappedElement().findElements(By.name(key));
        if (elements.isEmpty()) {
            return null;
        } else {
            return elements.get(0);
        }
    }

    protected void fillElement(WebElement element, String value) {
        String elementType = getElementType(element);

        if (CHECKBOX_FIELD.equals(elementType)) {
            fillCheckBox(element, value);
        } else if (RADIO_FIELD.equals(elementType)) {
            fillRadio(element, value);
        } else if (INPUT_FIELD.equals(elementType)) {
            fillInput(element, value);
        } else if (SELECT_FIELD.equals(elementType)) {
            fillSelect(element, value);
        } else if (FILE_FIELD.equals(elementType)) {
            fillFile(element, value);
        }
    }

    protected String getElementType(WebElement element) {
        String tagName = element.getTagName();
        if ("input".equals(tagName)) {
            String type = element.getAttribute("type");
            if ("checkbox".equals(type)) {
                return CHECKBOX_FIELD;
            } else if ("radio".equals(type)) {
                return RADIO_FIELD;
            } else if ("file".equals(type)) {
                return FILE_FIELD;
            } else {
                return INPUT_FIELD;
            }
        } else if ("select".equals(tagName)) {
            return SELECT_FIELD;
        } else if ("textarea".equals(tagName)) {
            return INPUT_FIELD;
        } else {
            return null;
        }
    }

    protected void fillCheckBox(WebElement element, String value) {
        new CheckBox(element).set(Boolean.parseBoolean(value));
    }

    protected void fillRadio(WebElement element, String value) {
        new Radio(element).selectByValue(value);
    }

    protected void fillInput(WebElement element, String value) {
        TextInput input = new TextInput(element);
        input.sendKeys(input.getClearCharSequence() + value);
    }

    protected void fillSelect(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void fillFile(WebElement element, String value) {
        new FileInput(element).setFileToUpload(value);
    }
}
