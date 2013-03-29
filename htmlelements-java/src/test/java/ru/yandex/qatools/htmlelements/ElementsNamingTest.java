package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;
import ru.yandex.qatools.htmlelements.testpages.SearchPage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 30.03.13
 */
public class ElementsNamingTest {
    private final SearchPage searchPage = new SearchPage();

    @Test
    public void htmlElementShouldHaveNameAsSpecifiedByAnnotation() {
        assertThat(searchPage.getSearchArrow().toString(), is(SearchArrowData.SEARCH_ARROW_NAME));
    }

    @Test
    public void wrappedElementOfHtmlElementShouldHaveNameAsSpecifiedByAnnotation() {
        assertThat(searchPage.getSearchArrow().getWrappedElement().toString(), is(SearchArrowData.SEARCH_ARROW_NAME));
    }

    @Test
    public void typifiedElementShouldHaveNameAsSpecifiedByAnnotation() {
        assertThat(searchPage.getSearchArrow().getRequestInput().toString(), is(SearchArrowData.REQUEST_INPUT_NAME));
    }

    @Test
    public void wrappedElementOfTypifiedElementShouldHaveNameAsSpecifiedByAnnotation() {
        assertThat(searchPage.getSearchArrow().getRequestInput().getWrappedElement().toString(),
                is(SearchArrowData.REQUEST_INPUT_NAME));
    }

    @Test
    public void webElementShouldHaveNameAsSpecifiedByAnnotation() {
        assertThat(searchPage.getLogo().toString(), is(SearchPage.LOGO_NAME));
    }

    @Test
    public void notAnnotatedTypifiedElementShouldHaveNameObtainedByFiledName() {
        assertThat(searchPage.getSearchArrow().getSearchButton().toString(), is("Search Button"));
    }

    @Test
    public void wrappedElementOfNotAnnotatedTypifiedElementShouldHaveNameObtainedByFiledName() {
        assertThat(searchPage.getSearchArrow().getSearchButton().getWrappedElement().toString(), is("Search Button"));
    }
}
