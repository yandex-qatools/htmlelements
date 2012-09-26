package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.AnnotatedHtmlElementPage;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class AnnotatedHtmlElementPageTest {
    private AnnotatedHtmlElementPage page = new AnnotatedHtmlElementPage();

    @Test
    public void htmlElementOnPageMustBeNotNull() {
        assertThat(page.getElement(), notNullValue());
    }

    @Test
    public void htmlElementWrappedElementMustBeNotNull() {
        assertThat(page.getElement().getWrappedElement(), notNullValue());
    }

    @Test
    public void htmlElementTagNameMustBeAsDeclared() {
        assertThat(page.getElement().getTagName(), equalTo(AnnotatedHtmlElementPage.HTML_ELEMENT_TAG_NAME));
    }
}
