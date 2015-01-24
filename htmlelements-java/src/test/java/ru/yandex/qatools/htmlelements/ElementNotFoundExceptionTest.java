package ru.yandex.qatools.htmlelements;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.DummyBlock;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.StringContains.containsString;

/**
 * User: lanwen
 * Date: 23.01.15
 * Time: 12:47
 */
public class ElementNotFoundExceptionTest {


    public static final String AJAX_ELEM_LOCATOR_EXC_START = "Timed out after";
    private WebDriver driver;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
    public void shouldWriteSelectorToExceptionMessage() throws Exception {
        thrown.expectMessage(
                both(containsString(DummyBlock.DUMMY_SELECTOR))
                .and(startsWith(AJAX_ELEM_LOCATOR_EXC_START)));

        HtmlElementLoader.create(DummyBlock.class, driver).click();
    }
}
