package ru.yandex.qatools.htmlelements.thucydides;

import com.google.common.base.Predicate;
import net.thucydides.core.ThucydidesSystemProperties;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * @author Artem Eroshenko eroshenkoam
 *         5/6/13, 6:02 PM
 */
public class BlockPageObject extends PageObject {

    public BlockPageObject(final WebDriver driver) {
        super(driver, new Predicate<PageObject>() {
            @Override
            public boolean apply(PageObject pageObject) {

                PageFactory.initElements(new HtmlElementDecorator(driver, getImplicitWaitTimeOut()), pageObject);
                return true;
            }
        });
    }

    private static int getImplicitWaitTimeOut() {
        try {
            return Integer.valueOf(ThucydidesSystemProperties.getProperties()
                    .getValue(ThucydidesSystemProperty.TIMEOUTS_IMPLICIT_WAIT));
        } catch (NumberFormatException ex) {
            return 5;
        }
    }
}

