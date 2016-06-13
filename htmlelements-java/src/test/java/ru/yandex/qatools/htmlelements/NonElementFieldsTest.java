package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.NonElementFieldsPage;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Graham Russell graham@ham1.co.uk
 *         Date: 24.10.2015
 */
public class NonElementFieldsTest {

    @Test
    public void nonElementPrivateFieldsShouldNotBeDecoratedAsElements() {

        NonElementFieldsPage page = new NonElementFieldsPage();

        assertThat("Non-WebElement/HtmlElement fields are not touched",
                page.rowsCache, is(nullValue()));
        assertThat("Non-WebElement/HtmlElement fields are not touched",
                page.rowsAsStringCache, is(nullValue()));
        assertThat("Non-WebElement/HtmlElement fields are not touched",
                page.otherCache, is(emptyCollectionOf(String.class)));
    }
}
