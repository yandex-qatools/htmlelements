package ru.yandex.qatools.htmlelements;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.Company;
import ru.yandex.qatools.htmlelements.testelements.PopularCompanies;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public class ComplexStructTest {
    public static final String PAGER_CSS = "div[class='pager']";
    public static final String WRAPPER_CSS = "div[class='wrapper']";
    public static final String COMPANY_CSS = "div[class='company']";
    public static final String VACANCY_CSS = "div[class='vacancy']";

    public static final String WRAPPER_NAME = "Popular companies wrapper";
    public static final String COMPANIES_LIST_NAME = "Popular companies list";
    public static final String VACANCY_LIST_NAME = "List of vacancies";

    private static final int COMPANIES_NUMBER = 3;
    private static final int VACANCIES_NUMBER = 4;

    private WebDriver webDriver;

    @Before
    public void before() {
        webDriver = mock(WebDriver.class);

        WebElement wrapper = mock(WebElement.class);
        WebElement company1 = mock(WebElement.class);
        WebElement company2 = mock(WebElement.class);
        WebElement company3 = mock(WebElement.class);
        WebElement vacancy = mock(WebElement.class);
        WebElement pager = mock(WebElement.class);

        List<WebElement> companies = Arrays.asList(company1, company2, company3);
        List<WebElement> vacancies = Arrays.asList(vacancy, vacancy, vacancy, vacancy);

        when(webDriver.findElement(By.cssSelector(WRAPPER_CSS))).thenReturn(wrapper);
        when(wrapper.findElement(By.cssSelector(PAGER_CSS))).thenReturn(pager);
        when(wrapper.findElements(By.cssSelector(COMPANY_CSS))).thenReturn(companies);
        when(company1.findElements(By.cssSelector(VACANCY_CSS))).thenReturn(vacancies);
        when(company2.findElements(By.cssSelector(VACANCY_CSS))).thenReturn(vacancies);
        when(company3.findElements(By.cssSelector(VACANCY_CSS))).thenReturn(vacancies);
    }

    @Test
    public void test() {
        PopularCompanies wrapper = HtmlElementLoader.create(PopularCompanies.class, webDriver);

        assertThat("Wrong company number has been received", wrapper.companies.size(), is(COMPANIES_NUMBER));

        for (Company company : wrapper.companies) {
            assertThat("Wrong vacancy number has been received", company.vacancyList.size(), is(VACANCIES_NUMBER));
        }

        assertThat(wrapper.toString(), is(WRAPPER_NAME));
        assertThat(wrapper.companies.toString(), is(COMPANIES_LIST_NAME));
        assertThat(wrapper.companies.get(0).toString(), is(String.format("%s [%d]", COMPANIES_LIST_NAME, 0)));
        assertThat(wrapper.companies.get(0).vacancyList.toString(), is(VACANCY_LIST_NAME));
    }
}
