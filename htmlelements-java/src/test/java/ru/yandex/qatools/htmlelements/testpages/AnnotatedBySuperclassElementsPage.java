package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.ComplexStructTest;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.NotAnnotatedExtendedCompany;
import ru.yandex.qatools.htmlelements.testelements.NotAnnotatedExtendedSearchArrow;
import ru.yandex.qatools.htmlelements.testelements.SearchArrowData;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.02.13
 */
public class AnnotatedBySuperclassElementsPage {
    private static final int COMPANIES_NUMBER = 3;

    private NotAnnotatedExtendedSearchArrow searchArrow;

    private List<NotAnnotatedExtendedCompany> companyList;

    public AnnotatedBySuperclassElementsPage() {
        this(mockDriver());
    }

    public AnnotatedBySuperclassElementsPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }

    public NotAnnotatedExtendedSearchArrow getSearchArrow() {
        return searchArrow;
    }

    public List<NotAnnotatedExtendedCompany> getCompanyList() {
        return companyList;
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);
        WebElement searchArrowElement = mock(WebElement.class);

        when(driver.findElement(By.className(SearchArrowData.SEARCH_ARROW_CLASS))).thenReturn(searchArrowElement);

        List<WebElement> companyElements = new ArrayList<>(COMPANIES_NUMBER);
        for (int i = 0; i < COMPANIES_NUMBER; i++) {
            companyElements.add(mock(WebElement.class));
        }

        when(driver.findElements(By.cssSelector(ComplexStructTest.COMPANY_CSS))).thenReturn(companyElements);

        return driver;
    }
}
