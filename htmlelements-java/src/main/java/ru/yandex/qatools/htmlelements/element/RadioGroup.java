package ru.yandex.qatools.htmlelements.element;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 * Represents a group of radio buttons.
 *
 * @author yuriy.shakhov  (shakhovyura@gmail.com)
 */
public class RadioGroup extends TypifiedElement{

	/**
     * Specifies a radio button of a radio button group that will be used to find all other buttons of this group.
     *
     * @param wrappedElement {@code WebElement} representing radio button.
     */
	public RadioGroup(WebElement wrappedElement) {
		super(wrappedElement);
	}
	
	/**
     * Returns all radio buttons belonging to this group.
     *
     * @return A list of {@code WebElements} representing radio buttons.
     */
	public List<WebElement> getButtons() {
		try {
			String xpath = ".//input[@type='radio']";
			List<WebElement> radioList = this.getWrappedElement().findElements(By.xpath(xpath));
			return radioList;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Cannot find radioButtons in this group");
		}
	}
	
	
	public void selectByText(String text) {
		try {
		String xpath = ".//*[normalize-space()='"+text+"']/input[@type='radio']";
		WebElement radio = this.getWrappedElement().findElement(By.xpath(xpath));
		selectButton(radio);	
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Cannot find radioButton with this text: '" + text + "'.");
		}
	}
	
	/**
     * Returns selected radio button.
     *
     * @return {@code WebElement} representing selected radio button or {@code null} if no radio buttons are selected.
     */
	public WebElement getSelectedButton() {
        for (WebElement button : getButtons()) {
            if (button.isSelected()) {
                return button;
            }
        }
        throw new NoSuchElementException("No selected button");
    }

	/**
     * Selects radio button that have a value matching the specified argument.
     *
     * @param value The value to match against.
     */
    public void selectByValue(String value) {
    	String xpath = ".//input[@type='radio' and @value='"+value+"']";
		WebElement radio = this.getWrappedElement().findElement(By.xpath(xpath));
		selectButton(radio);
    }

    /**
     * Selects radio button by the given index.
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
     * Selects radio button if it's not already selected.
     *
     * @param button {@code WebElement} representing radio button to be selected.
     */
    private void selectButton(WebElement button) {
        if (!button.isSelected()) {
            button.click();
        }
    }
}
