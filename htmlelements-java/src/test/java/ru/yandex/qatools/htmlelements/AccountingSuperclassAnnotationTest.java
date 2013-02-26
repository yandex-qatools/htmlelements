package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.NotAnnotatedExtendedSearchArrow;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.02.13
 */
@RunWith(Parameterized.class)
public class AccountingSuperclassAnnotationTest {
    private static WebDriver driver = SearchPage.mockDriver();

    private final NotAnnotatedExtendedSearchArrow searchArrow;

    public AccountingSuperclassAnnotationTest(NotAnnotatedExtendedSearchArrow searchArrow) {
        this.searchArrow = searchArrow;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        NotAnnotatedExtendedSearchArrow createdSearchArrow = HtmlElementLoader.create(
                NotAnnotatedExtendedSearchArrow.class, driver);

        NotAnnotatedExtendedSearchArrow populatedSearchArrow = new NotAnnotatedExtendedSearchArrow();
        HtmlElementLoader.populate(populatedSearchArrow, driver);

        return Arrays.asList(new Object[][]{{createdSearchArrow}, {populatedSearchArrow}});
    }

    @Test
    public void htmlElementWithAnnotatedSuperclassShouldBeNotNull() {
        assertThat(searchArrow.getWrappedElement(), is(notNullValue()));
    }
}
