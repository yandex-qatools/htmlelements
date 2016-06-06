package ru.yandex.qatools.htmlelements;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.DummyBlock;

/**
 * Test that validates the {@link TypifiedElement} isDisplayed method always
 * returns a boolean.
 * @author Michael Suzuki
 */
public class ElementDisplayedTest {

    private WebDriver driver;
    
    @Rule
    public ExternalResource manage = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            driver = new PhantomJSDriver();
        }

        @Override
        protected void after() {
            driver.quit();
        }
    };
    
    @Test
    public void shouldReturnFalseAndNotAnException() throws Exception {
        Assert.assertFalse(HtmlElementLoader.create(DummyBlock.class, driver).isDisplayed());
    }

    @Test
    public void shouldReturnTrueForVisibleElements() throws Exception {
        WebElement element = mock(WebElement.class);
        when(element.isDisplayed()).thenReturn(true);
        HtmlElement elementVisble = new HtmlElement();
        elementVisble.setWrappedElement(element);
        Assert.assertTrue(elementVisble.isDisplayed());
        
        when(element.isDisplayed()).thenReturn(false);
        Assert.assertFalse(elementVisble.isDisplayed());
    }

}
