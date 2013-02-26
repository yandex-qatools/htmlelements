package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.mockito.Mockito.mock;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */

public class NotAnnotatedFieldsPage {

    private HtmlElement htmlElement;

    private WebElement webElement;

    private CheckBox checkBox;

    private Select select;

    public NotAnnotatedFieldsPage() {
        this(mockDriver());
    }

    public NotAnnotatedFieldsPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public HtmlElement getHtmlElement() {
        return htmlElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public Select getSelect() {
        return select;
    }

    public static WebDriver mockDriver() {
        return mock(WebDriver.class);
    }
}
