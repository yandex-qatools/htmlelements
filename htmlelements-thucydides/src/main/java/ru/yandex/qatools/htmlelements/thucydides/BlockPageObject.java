package ru.yandex.qatools.htmlelements.thucydides;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 6:02 PM
 */
public class BlockPageObject extends PageObject {

    public BlockPageObject(final WebDriver driver) {
        super(driver, pageObject -> {
            PageFactory.initElements(
                    new HtmlElementDecorator(
                            new HtmlElementLocatorFactory(driver)), pageObject);
            return true;
        });
    }
}

