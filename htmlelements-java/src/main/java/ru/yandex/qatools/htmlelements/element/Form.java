package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents web page form tag. Provides handy way of filling form with data and submitting it.
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
     * For each map entry an input with a name coincident with entry key is searched and then found input
     * is filled with string representation of entry value (toString() method is called). If input with such a name
     * is not found corresponding entry is skipped.
     *
     * @param data Map containing data to fill form inputs with.
     */
    public void fill(Map<String, Object> data) {
        for (String key : data.keySet()) {
            WebElement elementToFill = findElementByKey(key);
            if (elementToFill != null) {
                fillElement(elementToFill, Objects.toString(data.get(key), ""));
            }
        }
    }

    /**
     * Submits represented form.
     */
    public void submit() {
        getWrappedElement().submit();
    }

    protected WebElement findElementByKey(String key) {
        List<WebElement> elements = getWrappedElement().findElements(By.name(key));
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0);
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
            }
            if ("radio".equals(type)) {
                return RADIO_FIELD;
            }
            if ("file".equals(type)) {
                return FILE_FIELD;
            }
            return INPUT_FIELD;
        }
        if ("select".equals(tagName)) {
            return SELECT_FIELD;
        }
        if ("textarea".equals(tagName)) {
            return INPUT_FIELD;
        }
        return null;
    }

    protected void fillCheckBox(WebElement element, String value) {
        CheckBox checkBox = new CheckBox(element);
        checkBox.set(Boolean.parseBoolean(value));
    }

    protected void fillRadio(WebElement element, String value) {
        Radio radio = new Radio(element);
        radio.selectByValue(value);
    }

    protected void fillInput(WebElement element, String value) {
        TextInput input = new TextInput(element);
        input.sendKeys(input.getClearCharSequence() + value);
    }

    protected void fillSelect(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    protected void fillFile(WebElement element, String value) {
        FileInput fileInput = new FileInput(element);
        fileInput.setFileToUpload(value);
    }
}
