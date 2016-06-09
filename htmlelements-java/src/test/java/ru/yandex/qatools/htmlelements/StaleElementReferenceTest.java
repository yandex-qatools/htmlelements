package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.StaleElementReferencePage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
public class StaleElementReferenceTest {

    @Test
    public void testOutput() {
        StaleElementReferencePage staleElementReferencePage = new StaleElementReferencePage();
        assertThat(staleElementReferencePage.getElement().getText(), equalTo(StaleElementReferencePage.ELEMENT_TEXT));
    }

}
