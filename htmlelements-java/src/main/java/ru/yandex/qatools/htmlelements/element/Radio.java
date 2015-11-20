package ru.yandex.qatools.htmlelements.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

/**
 * Represents a group of radio buttons.
 *
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 14.08.12
 */
public class Radio extends TypifiedElement {
    /**
     * Specifies a radio button of a radio button group that will be used to find all other buttons of this group.
     *
     * @param wrappedElement {@code WebElement} representing radio button.
     */
    public Radio(WebElement wrappedElement) {
        super(wrappedElement);
    }

    /**
     * Returns all radio buttons belonging to this group.
     *
     * @return A list of {@code WebElements} representing radio buttons.
     */
    public List<WebElement> getButtons() {
        String radioName = getWrappedElement().getAttribute("name");

        String xpath;
        if (radioName == null) {
            xpath = "self::* | following::input[@type = 'radio'] | preceding::input[@type = 'radio']";
        } else {
            xpath = String.format(
                    "self::* | following::input[@type = 'radio' and @name = '%s'] | " +
                            "preceding::input[@type = 'radio' and @name = '%s']",
                    radioName, radioName);
        }

        return getWrappedElement().findElements(By.xpath(xpath));
    }

    /**
     * Returns selected radio button.
     *
     * @return {@code WebElement} representing selected radio button or {@code null} if no radio buttons are selected.
     */
    public WebElement getSelectedButton() {
        return getButtons().stream()
                .filter(WebElement::isSelected)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No selected button"));
    }

    /**
     * Indicates if radio group has selected button.
     *
     * @return {@code true} if radio has selected button and {@code false} otherwise.
     */
    public boolean hasSelectedButton() {
        return getButtons().stream()
                .anyMatch(WebElement::isSelected);
    }

    /**
     * Selects first radio button that has a value matching the specified argument.
     *
     * @param value The value to match against.
     */
    public void selectByValue(String value) {
        Optional<WebElement> matchingButton = getButtons().stream()
                .filter(b -> value.equals(b.getAttribute("value")))
                .findFirst();

        selectButton(matchingButton
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Cannot locate radio button with value: %s", value))));
    }

    /**
     * Selects a radio button by the given index.
     *
     * @param index Index of a radio button to be selected.
     */
    public void selectByIndex(int index) {
        List<WebElement> buttons = getButtons();

        if (index < 0 || index >= buttons.size()) {
            throw new NoSuchElementException(String.format("Cannot locate radio button with index: %d", index));
        }

        selectButton(buttons.get(index));
    }

    /**
     * Selects a radio button if it's not already selected.
     *
     * @param button {@code WebElement} representing radio button to be selected.
     */
    private void selectButton(WebElement button) {
        if (!button.isSelected()) {
            button.click();
        }
    }
}
