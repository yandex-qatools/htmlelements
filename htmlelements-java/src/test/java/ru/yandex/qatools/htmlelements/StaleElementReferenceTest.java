package ru.yandex.qatools.htmlelements;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.StaleElementReferenceException;
import ru.yandex.qatools.htmlelements.testpages.StaleElementReferencePage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
public class StaleElementReferenceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testOutput() {
        StaleElementReferencePage staleElementReferencePage = new StaleElementReferencePage();
        thrown.expect(StaleElementReferenceException.class);
        staleElementReferencePage.getElement().getText();
    }

}
