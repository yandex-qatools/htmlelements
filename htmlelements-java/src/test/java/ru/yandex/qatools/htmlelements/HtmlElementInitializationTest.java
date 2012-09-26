package ru.yandex.qatools.htmlelements;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 20.08.12
 */
public class HtmlElementInitializationTest {
    private WebDriver driver = SearchPage.mockDriver();

    @Test
    public void testSearchArrowInitialization() {
        SearchArrow searchArrow = new SearchArrow();
        HtmlElementLoader.populateHtmlElement(searchArrow, driver);

        assertThat("Wrapped element of search arrow should not be null",
                searchArrow.getWrappedElement(), is(notNullValue()));
        assertThat("Request input should not be null",
                searchArrow.getRequestInput(), is(notNullValue()));
        assertThat("Submit button should not be null", searchArrow.getSearchButton(), is(notNullValue()));
    }

    @Test
    public void testSearchArrowCreationAndInitialization() {
        SearchArrow searchArrow = HtmlElementLoader.createHtmlElement(SearchArrow.class, driver);

        assertThat("Wrapped element of search arrow should not be null",
                searchArrow.getWrappedElement(), is(notNullValue()));
        assertThat("Request input should not be null",
                searchArrow.getRequestInput(), is(notNullValue()));
        assertThat("Submit button should not be null", searchArrow.getSearchButton(), is(notNullValue()));
    }
}
