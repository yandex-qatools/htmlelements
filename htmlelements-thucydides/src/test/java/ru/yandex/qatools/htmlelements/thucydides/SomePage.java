package ru.yandex.qatools.htmlelements.thucydides;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 6:11 PM
 */
public class SomePage extends BlockPageObject {

    @FindBy(className = "block")
    public SomeBlockElement blockElement;

    public SomePage(WebDriver driver) {
        super(driver);
    }
}
