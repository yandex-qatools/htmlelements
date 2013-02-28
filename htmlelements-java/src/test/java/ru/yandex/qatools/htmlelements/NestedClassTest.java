package ru.yandex.qatools.htmlelements;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: eroshenkoam
 * Date: 1/22/13, 2:16 PM
 */
public class NestedClassTest {
    public WebDriver driver = mock(WebDriver.class);
    WebElement arrow = mock(WebElement.class);
    WebElement link = mock(WebElement.class);

    @Before
    public void before() {
        when(driver.findElement(By.className("arrow"))).thenReturn(arrow);
        when(arrow.findElement(By.className("link"))).thenReturn(link);
    }

    @Test
    public void test() {
        NestedPageObject pageObject = new NestedPageObject();
        HtmlElementLoader.populate(pageObject, driver);

        assertThat(pageObject, notNullValue());
        assertThat(pageObject.arrow.getName(), notNullValue());
        assertThat(pageObject.arrow.link.getWrappedElement(), notNullValue());
    }

    public class NestedPageObject {

        @FindBy(className = "arrow")
        public NestedHtmlElement arrow;
    }

    public class NestedHtmlElement extends HtmlElement {
        @FindBy(className = "link")
        public NestedTypifiedElement link;
    }

    public class NestedTypifiedElement extends TypifiedElement {

        public NestedTypifiedElement(WebElement wrappedElement) {
            super(wrappedElement);
        }

    }
}
