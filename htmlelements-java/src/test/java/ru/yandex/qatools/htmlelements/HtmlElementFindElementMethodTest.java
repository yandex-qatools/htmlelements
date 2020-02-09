package ru.yandex.qatools.htmlelements;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.Company;
import ru.yandex.qatools.htmlelements.testelements.PopularCompanies;
import ru.yandex.qatools.htmlelements.testelements.SearchArrow;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class HtmlElementFindElementMethodTest {

    private final WebDriver webDriver = mock(WebDriver.class);
    private final SearchArrow searchArrow = new SearchArrow();
    private final PopularCompanies popularCompanies = new PopularCompanies();

    @Before
    public void setUp() {
        HtmlElementLoader.populateHtmlElement(searchArrow, webDriver);
        HtmlElementLoader.populateHtmlElement(popularCompanies, webDriver);
    }

    @Test
    public void findElementShouldReturnObjectWithSameValue() {
        List<Company> actualList = new ArrayList<>();
        popularCompanies.companies = actualList;

        List<Company> expectedList = popularCompanies.findElement(ComplexStructTest.COMPANIES_LIST_NAME);

        assertThat(actualList, is(equalTo(expectedList)));
    }

    @Test
    public void findElementShouldReturnObjectWithSameType() {
        Object foundElement = searchArrow.findElement(SearchArrowData.REQUEST_INPUT_NAME);
        assertThat(foundElement, instanceOf(TextInput.class));
    }

    @Test(expected = NoSuchElementException.class)
    public void findElementShouldReturnNullIfElementNotFound() {
        popularCompanies.findElement("Nonexistent element");
    }
}
