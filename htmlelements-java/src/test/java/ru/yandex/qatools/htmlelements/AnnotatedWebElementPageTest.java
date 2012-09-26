package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.AnnotatedWebElementPage;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class AnnotatedWebElementPageTest {
    private AnnotatedWebElementPage page = new AnnotatedWebElementPage();

    @Test
    public void webElementOnPageMustBeNotNull() {
        assertThat(page.getElement(), notNullValue());
    }

    @Test
    public void webElementTagNameMustBeAsDeclared() {
        assertThat(page.getElement().getTagName(), equalTo(AnnotatedWebElementPage.ELEMENT_TAG_NAME));
    }
}
