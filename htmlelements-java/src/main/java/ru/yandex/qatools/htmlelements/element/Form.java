package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * @author Artem Eroshenko eroshenkoam
 *         3/14/13, 4:38 PM
 */
public class Form extends TypifiedElement {
    protected final static String TEXT_TYPE = "text";
    protected final static String PASSWORD_TYPE = "password";
    protected final static String CHECKBOX_TYPE = "checkbox";

    /**
     * Specifies wrapped {@link org.openqa.selenium.WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public Form(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void fill(HashMap<String, Object> data) {
        for (String name : data.keySet()) {
            List<WebElement> elements = getWrappedElement().findElements(By.name(name));
            if (elements.size() > 0) {
                fillInput(elements.get(0), data.get(name));
            }
        }
        getWrappedElement().submit();
    }

    protected void fillInput(WebElement input, Object value) {
        if (!input.getTagName().equals("input")) return;

        String type = input.getAttribute("type");
        if (type.equals(TEXT_TYPE) || type.equals(PASSWORD_TYPE)) {
            if (value != null) {
                input.sendKeys(value.toString());
            }
        } else if (type.equals(CHECKBOX_TYPE)) {
            // TODO: need add method set(boolean status) to Checkbox
            if (Boolean.parseBoolean(value.toString())) {
                new CheckBox(input).select();
            } else {
                new CheckBox(input).deselect();
            }
        } else {
            // do nothing
        }
    }
}
