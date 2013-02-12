package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.testpages.NamedHtmlElementListPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 12.02.13
 */
public class HtmlElementListItemNamesTest {
    private NamedHtmlElementListPage page = new NamedHtmlElementListPage();

    @Test
    public void htmlElementListItemsShouldHaveCorrectNames() {
        for (int i = 0; i < NamedHtmlElementListPage.getCompaniesNumber(); i++) {
            HtmlElement item = page.getPopularCompaniesList().get(i);
            String expectedName = String.format("%s [%d]", NamedHtmlElementListPage.getPopularCompaniesListName(), i);
            assertThat(String.format("Item should have name %s", expectedName),
                    item.getName(), is(expectedName));
        }
    }
}
