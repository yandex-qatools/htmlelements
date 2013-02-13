package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowWithRequestSample;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.02.13
 */
@RunWith(Parameterized.class)
public class InheritedHtmlElementInitializationTest {
    private static WebDriver driver = SearchPage.mockDriver();

    private SearchArrowWithRequestSample inheritedSearchArrow;

    public InheritedHtmlElementInitializationTest(SearchArrowWithRequestSample inheritedSearchArrow) {
        this.inheritedSearchArrow = inheritedSearchArrow;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        SearchArrowWithRequestSample createdSearchArrow = HtmlElementLoader.create(SearchArrowWithRequestSample.class, driver);

        SearchArrowWithRequestSample populatedSearchArrow = new SearchArrowWithRequestSample();
        HtmlElementLoader.populate(populatedSearchArrow, driver);

        return Arrays.asList(new Object[][]{{createdSearchArrow}, {populatedSearchArrow}});
    }

    @Test
    public void wrappedElementOfInheritedHtmlElementShouldNotBeNull() {
        assertThat("Wrapped element of search arrow should not be null",
                inheritedSearchArrow.getWrappedElement(), is(notNullValue()));
    }

    @Test
    public void innerElementsOfInheritedHtmlElementShouldNotBeNull() {
        assertThat("Request input of search arrow should not be null",
                inheritedSearchArrow.getRequestInput(), is(notNullValue()));
        assertThat("Submit button of search arrow should not be null",
                inheritedSearchArrow.getSearchButton(), is(notNullValue()));
        assertThat("Search request sample of search arrow should not be null",
                inheritedSearchArrow.getSearchRequestSample(), is(notNullValue()));
    }
}
