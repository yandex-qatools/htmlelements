package ru.yandex.qatools.htmlelements.matchers.testpages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.matchers.testelements.CustomBlock;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class PageWithCustomBlock {
    private CustomBlock block;

    public PageWithCustomBlock(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public CustomBlock getBlock() {
        return block;
    }
}
