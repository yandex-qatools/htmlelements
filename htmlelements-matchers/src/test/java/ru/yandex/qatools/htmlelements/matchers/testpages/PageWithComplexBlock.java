package ru.yandex.qatools.htmlelements.matchers.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.matchers.testelements.ExistingBlock;
import ru.yandex.qatools.htmlelements.matchers.testelements.NotExistingBlock;
import ru.yandex.qatools.htmlelements.matchers.testelements.CustomBlock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class PageWithComplexBlock {

    private ExistingBlock existingBlock;

    private NotExistingBlock notExistingBlock;

    public ExistingBlock getExistingBlock() {
        return existingBlock;
    }

    public NotExistingBlock getNotExistingBlock() {
        return notExistingBlock;
    }

    public PageWithComplexBlock(WebDriver webDriver) {
        HtmlElement parent = mock(HtmlElement.class);
        HtmlElement child = mock(HtmlElement.class);
        when(webDriver.findElement(By.className(NotExistingBlock.CLASS_NAME))).thenReturn(parent);
        when(parent.findElement(By.className(CustomBlock.CLASS_NAME))).thenReturn(child);
        HtmlElementLoader.populatePageObject(this, webDriver);
    }
}
