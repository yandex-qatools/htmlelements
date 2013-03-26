package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         3/14/13, 4:38 PM
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.03.13
 */
// TODO Add JavaDoc
public class Form extends TypifiedElement {
    private final static String TEXT_INPUT_TYPE = "text";
    private final static String PASSWORD_INPUT_TYPE = "password";
    private final static String TEXT_AREA_TYPE = "textarea";
    private final static String CHECKBOX_TYPE = "checkbox";
    private final static String SELECT_TYPE = "select";
    private final static String RADIO_TYPE = "radio";

    /**
     * Specifies wrapped {@link org.openqa.selenium.WebElement}.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public Form(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void fill(HashMap<String, Object> data) {
        for (String key : data.keySet()) {
            WebElement elementToFill = findElementByKey(key);
            if (elementToFill != null && isInput(elementToFill)) {
                fillInput(elementToFill, data.get(key));
            }
        }
        getWrappedElement().submit();
    }

    private boolean isInput(WebElement element) {
        return "input".equals(element.getTagName());
    }

    protected WebElement findElementByKey(String key) {
        List<WebElement> elements = getWrappedElement().findElements(By.name(key));
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0);
    }

    protected void fillInput(WebElement input, Object value) {
        if (value == null) {
            return;
        }

        String inputType = input.getAttribute("type");
        if (inputType == null || inputType.equals(TEXT_INPUT_TYPE) || inputType.equals(PASSWORD_INPUT_TYPE) ||
                inputType.equals(TEXT_AREA_TYPE)) {
            input.sendKeys(value.toString());
        } else if (inputType.equals(CHECKBOX_TYPE)) {
            CheckBox checkBox = new CheckBox(input);
            checkBox.set(Boolean.parseBoolean(value.toString()));
        } else if (inputType.equals(SELECT_TYPE)) {
            Select select = new Select(input);
            select.selectByValue(value.toString());
        } else if (inputType.equals(RADIO_TYPE)) {
            Radio radio = new Radio(input);
            radio.selectByValue(value.toString());
        }
    }
}
