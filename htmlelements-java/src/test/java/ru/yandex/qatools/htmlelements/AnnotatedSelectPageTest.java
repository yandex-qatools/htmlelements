package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testpages.AnnotatedSelectPage;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 1.07.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class AnnotatedSelectPageTest {
    private AnnotatedSelectPage page = new AnnotatedSelectPage();

    @Test
    public void htmlElementOnPageMustBeNotNull() {
        assertThat(page.getSelect(), notNullValue());
    }
}
