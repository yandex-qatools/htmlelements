package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 20.08.12
 */
@RunWith(Parameterized.class)
public class HtmlElementInitializationTest {
    private static WebDriver driver = SearchPage.mockDriver();

    private SearchArrow searchArrow;

    public HtmlElementInitializationTest(SearchArrow searchArrow) {
        this.searchArrow = searchArrow;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        SearchArrow createdSearchArrow = HtmlElementLoader.createHtmlElement(SearchArrow.class, driver);

        SearchArrow populatedSearchArrow = new SearchArrow();
        HtmlElementLoader.populateHtmlElement(populatedSearchArrow, driver);

        return Arrays.asList(new Object[][]{{createdSearchArrow}, {populatedSearchArrow}});
    }

    @Test
    public void wrappedElementOfHtmlElementShouldNotBeNull() {
        assertThat("Wrapped element of search arrow should not be null",
                searchArrow.getWrappedElement(), is(notNullValue()));
    }

    @Test
    public void innerElementsOfHtmlElementShouldNotBeNull() {
        assertThat("Request input of search arrow should not be null",
                searchArrow.getRequestInput(), is(notNullValue()));
        assertThat("Submit button of search arrow should not be null",
                searchArrow.getSearchButton(), is(notNullValue()));
    }
}
