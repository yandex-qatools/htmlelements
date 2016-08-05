package ru.yandex.qatools.htmlelements.matchers;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.matchers.mockfactory.MockFactory;
import ru.yandex.qatools.htmlelements.matchers.testpages.PageWithComplexBlock;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author Pavel Zorin pazone@yandex-team.ru
 */

public class BlocksTest {
    private WebDriver driver = MockFactory.mockDriver();

    @Test
    public void blockExists() {
        PageWithComplexBlock page = new PageWithComplexBlock(driver);
        assertThat(page.getExistingBlock(), (BlockMatchers.subElementsExists(driver)));
    }

    @Test
    public void blockNotExists() {
        PageWithComplexBlock page = new PageWithComplexBlock(driver);
        assertThat(page.getNotExistingBlock(), not(BlockMatchers.subElementsExists(driver)));
    }
}
