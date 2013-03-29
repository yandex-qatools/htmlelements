package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Artem Eroshenko eroshenkoam@yandex-team.ru
 *         Date: 30.06.12
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
public class SampleTest {
    SearchPage searchPage = new SearchPage();

    @Test
    public void htmlElementOnPageMustBeNotNull() {
        assertThat(searchPage.getSearchArrow(), notNullValue());
    }

    @Test
    public void htmlElementWebElementFieldMustBeNotNull() {
        assertThat(searchPage.getSearchArrow().getRequestInput(), notNullValue());
    }

    @Test
    public void htmlElementHtmlElementFieldMustBeNotNull() {
        assertThat(searchPage.getSearchArrow().getSearchButton(), notNullValue());
    }

    @Test
    public void testLogoName() {
        assertThat(searchPage.getLogo().toString(), is(SearchPage.LOGO_NAME));
    }

    @Test
    public void testSearchArrowName() {
        assertThat(searchPage.getSearchArrow().toString(), is(SearchArrowData.SEARCH_ARROW_NAME));
    }

    @Test
    public void testSearchArrowRequestInputName() {
        assertThat(searchPage.getSearchArrow().getRequestInput().toString(), is(SearchArrowData.REQUEST_INPUT_NAME));
    }

    @Test
    public void testSearchArrowRequestButtonName() {
        assertThat(searchPage.getSearchArrow().getSearchButton().toString(), is("Search Button"));
    }
}
