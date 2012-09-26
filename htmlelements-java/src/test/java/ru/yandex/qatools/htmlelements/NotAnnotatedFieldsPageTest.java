package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.NotAnnotatedFieldsPage;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class NotAnnotatedFieldsPageTest {
    private NotAnnotatedFieldsPage page = new NotAnnotatedFieldsPage();

    @Test
    public void notAnnotatedWebElementMustBeNull() {
        assertThat(page.getWebElement(), nullValue());
    }

    @Test
    public void notAnnotatedHtmlElementMustBeNull() {
        assertThat(page.getHtmlElement(), nullValue());
    }

    @Test
    public void notAnnotatedCheckBoxMustBeNull() {
        assertThat(page.getCheckBox(), nullValue());
    }

    @Test
    public void notAnnotatedSelectMustBeNull() {
        assertThat(page.getSelect(), nullValue());
    }
}
