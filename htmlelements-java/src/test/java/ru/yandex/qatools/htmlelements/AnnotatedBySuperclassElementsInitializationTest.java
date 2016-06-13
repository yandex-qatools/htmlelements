package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.NotAnnotatedExtendedSearchArrow;
import ru.yandex.qatools.htmlelements.testpages.AnnotatedBySuperclassElementsPage;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.02.13
 */
public class AnnotatedBySuperclassElementsInitializationTest {
    private final WebDriver driver = AnnotatedBySuperclassElementsPage.mockDriver();

    @Test
    public void createdAnnotatedBySuperclassHtmlElementShouldBeInitialized() {
        NotAnnotatedExtendedSearchArrow searchArrow = HtmlElementLoader.create(NotAnnotatedExtendedSearchArrow.class, driver);

        assertThat(searchArrow.getWrappedElement(), is(notNullValue()));
    }

    @Test
    public void populatedAnnotatedBySuperclassHtmlElementShouldBeInitialized() {
        NotAnnotatedExtendedSearchArrow searchArrow = new NotAnnotatedExtendedSearchArrow();
        HtmlElementLoader.populate(searchArrow, driver);

        assertThat(searchArrow.getWrappedElement(), is(notNullValue()));
    }

    @Test
    public void annotatedBySuperclassElementsPageShouldBeInitialized() {
        AnnotatedBySuperclassElementsPage page = new AnnotatedBySuperclassElementsPage(driver);

        assertThat("HtmlElement annotated by superclass should be not null",
                page.getSearchArrow(), is(notNullValue()));
        assertThat("List annotated by parameter superclass  should be not null",
                page.getCompanyList(), is(notNullValue()));
    }
}
