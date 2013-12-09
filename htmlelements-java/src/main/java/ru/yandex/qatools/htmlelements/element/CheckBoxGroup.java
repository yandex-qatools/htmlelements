package ru.yandex.qatools.htmlelements.element;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * Represents a group of checkBox buttons.
 * Обязательно!!! указать через xpath элемент содержащий всю группу чекбоксов
 *
 * @author yuriy.shakhov  (shakhovyura@gmail.com)
 */
public class CheckBoxGroup extends TypifiedElement{

	/**
     * Specifies a checkBox button of a checkBox button group that will be used to find all other buttons of this group.
     *
     * @param wrappedElement {@code WebElement} representing checkBox button.
     */
	public CheckBoxGroup(WebElement wrappedElement) {
		super(wrappedElement);
	}
	
	/**
     * Returns all checkBox buttons belonging to this group.
     *
     * @return A list of {@code WebElements} representing checkBox buttons.
     */
	public List<WebElement> getCheckBoxes() {
		String xpath = ".//input[@type='checkbox']";
		List<WebElement> checkBoxList = this.getWrappedElement().findElements(By.xpath(xpath));
		return checkBoxList;
	}
	
	
	public void selectByText(String text) {
		String xpath = ".//*[normalize-space()='"+text+"']/input[@type='checkbox']";
                WebElement e = this.getWrappedElement();
		WebElement checkBox = e.findElement(By.xpath(xpath));
		selectCheckBox(checkBox);
	}
	
	/**
     * Returns selected checkBox button.
     *
     * @return {@code List<WebElement>} representing selected checkBox buttons or {@code null} if no checkBox buttons are selected.
     */
	public List<WebElement> getSelectedCheckBoxes() {
		List<WebElement> checkBoxList = new ArrayList<WebElement>();
        for (WebElement button : getCheckBoxes()) {
            if (button.isSelected()) {
            	checkBoxList.add(button);
            }
        }
        return checkBoxList;
    }

	/**
     * Selects checkBox button that have a value matching the specified argument.
     *
     * @param value The value to match against.
     */
    public void selectByValue(String value) {
    	String xpath = ".//input[@type='checkbox' and @value='"+value+"']";
		WebElement checkBox = this.getWrappedElement().findElement(By.xpath(xpath));
		selectCheckBox(checkBox);
    }

    /**
     * Selects checkBox button by the given index.
     *
     * @param index Index of a checkBox button to be selected.
     */
    public void selectByIndex(int index) {
        List<WebElement> buttons = getCheckBoxes();

        if (index < 0 || index >= buttons.size()) {
            throw new NoSuchElementException(String.format("Cannot locate checkBox button with index: %d", index));
        }

        selectCheckBox(buttons.get(index));
    }
    
    /**
     * Selects checkBox button if it's not already selected.
     * @param check если true, то проверяем выбран ли, иначе не проверям 
     * @param button {@code WebElement} representing checkBox button to be selected.
     */
    private void selectCheckBox(WebElement button, boolean check) {
        if (check){
        	if (!button.isSelected()) {
        		button.click();
        	}
        }
        else button.click();
    }
    
    /**
     * Selects checkBox button.
     *
     * @param button {@code WebElement} representing checkBox button to be selected.
     */
    private void selectCheckBox(WebElement button) {
            button.click();
    }
}
