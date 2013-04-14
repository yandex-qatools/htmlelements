package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         3/14/13, 4:38 PM
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.03.13
 */
// TODO Add JavaDoc
public class Form extends TypifiedElement {
    private static final String TEXT_INPUT_TYPE = "text";
    private static final String PASSWORD_INPUT_TYPE = "password";
    private static final String CHECKBOX_TYPE = "checkbox";
    private static final String RADIO_TYPE = "radio";

    /**
     * Specifies {@link org.openqa.selenium.WebElement} representing form tag.
     *
     * @param wrappedElement {@code WebElement} to wrap.
     */
    public Form(WebElement wrappedElement) {
        super(wrappedElement);
    }

    public void fill(Map<String, Object> data) {
        for (String key : data.keySet()) {
            WebElement elementToFill = findElementByKey(key);
            if (elementToFill != null) {
                fillElement(elementToFill, data.get(key));
            }
        }
    }

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

    protected void fillElement(WebElement element, Object value) {
        if (value == null) {
            return;
        }

        if (isInput(element)) {
            String inputType = element.getAttribute("type");
            if (inputType == null || inputType.equals(TEXT_INPUT_TYPE) || inputType.equals(PASSWORD_INPUT_TYPE)) {
                element.sendKeys(value.toString());
            } else if (inputType.equals(CHECKBOX_TYPE)) {
                CheckBox checkBox = new CheckBox(element);
                checkBox.set(Boolean.parseBoolean(value.toString()));
            } else if (inputType.equals(RADIO_TYPE)) {
                Radio radio = new Radio(element);
                radio.selectByValue(value.toString());
            }
        } else if (isSelect(element)) {
            Select select = new Select(element);
            select.selectByValue(value.toString());
        } else if (isTextArea(element)) {
            element.sendKeys(value.toString());
        }
    }

    private boolean isInput(WebElement element) {
        return "input".equals(element.getTagName());
    }

    private boolean isSelect(WebElement element) {
        return "select".equals(element.getTagName());
    }

    private boolean isTextArea(WebElement element) {
        return "textarea".equals(element.getTagName());
    }
}
