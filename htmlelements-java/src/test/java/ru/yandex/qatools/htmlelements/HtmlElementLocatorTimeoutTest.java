package ru.yandex.qatools.htmlelements;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;
import ru.yandex.qatools.htmlelements.testelements.ClassWithTimeout;
import ru.yandex.qatools.htmlelements.testelements.ParentTimeoutClass;

import static org.mockito.Mockito.mock;

public class HtmlElementLocatorTimeoutTest {
    private HtmlElementLocatorFactory factory;

    @Before
    public void createStubs() {
        factory = new HtmlElementLocatorFactory(mock(WebDriver.class));
    }

    @Test
    public void classTimeout() {
        Assert.assertEquals(2, factory.getTimeOut(ClassWithTimeout.class));
    }

    @Test
    public void parentTimeoutClass() {
        Assert.assertEquals(2, factory.getTimeOut(ParentTimeoutClass.class));
    }

    @Test
    public void classWithoutTimeout() {
        Assert.assertEquals(5, factory.getTimeOut(HtmlElement.class));
    }

    @Test
    public void classFieldsTimeout() throws NoSuchFieldException {
        Assert.assertEquals(2, factory.getTimeOut(ParentTimeoutClass.class.getDeclaredField("listElement")));
        Assert.assertEquals(2, factory.getTimeOut(ParentTimeoutClass.class.getDeclaredField("element")));
        Assert.assertEquals(2, factory.getTimeOut(ParentTimeoutClass.class.getDeclaredField("classElement")));
        Assert.assertEquals(5, factory.getTimeOut(ParentTimeoutClass.class.getDeclaredField("noTimeout")));
    }

    @Test
    public void timeoutWithSystemProperty() {
        final String propertyName = "webdriver.timeouts.implicitlywait";
        final String previousTimeout = System.getProperty(propertyName);
        System.setProperty(propertyName, "1");
        try {
            Assert.assertEquals(1, factory.getTimeOut(HtmlElement.class));
        } finally {
            if (previousTimeout == null) {
                System.getProperties().remove(propertyName);
            } else {
                System.setProperty(propertyName, previousTimeout);
            }
        }
    }
}
