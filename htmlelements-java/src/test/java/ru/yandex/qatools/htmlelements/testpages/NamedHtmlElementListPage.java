package ru.yandex.qatools.htmlelements.testpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testelements.PopularCompanies;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 12.02.13
 */
public class NamedHtmlElementListPage {
    private static final String POPULAR_COMPANIES_LIST_NAME = "Popular companies";
    private static final String POPULAR_COMPANIES_CLASS_NAME = "popular-companies";
    private static final int COMPANIES_NUMBER = 5;

    @Name(POPULAR_COMPANIES_LIST_NAME)
    @FindBy(className = POPULAR_COMPANIES_CLASS_NAME)
    private List<PopularCompanies> popularCompaniesList;

    public NamedHtmlElementListPage() {
        this(mockDriver());
    }

    public NamedHtmlElementListPage(WebDriver driver) {
        HtmlElementLoader.populatePageObject(this, driver);
    }

    public List<PopularCompanies> getPopularCompaniesList() {
        return popularCompaniesList;
    }

    public static String getPopularCompaniesListName() {
        return POPULAR_COMPANIES_LIST_NAME;
    }

    public static int getCompaniesNumber() {
        return COMPANIES_NUMBER;
    }

    private static WebDriver mockDriver() {
        WebDriver driver = mock(WebDriver.class);

        List<WebElement> companyWebElements = new ArrayList<>(COMPANIES_NUMBER);
        for (int i = 0; i < COMPANIES_NUMBER; i++) {
            companyWebElements.add(mock(WebElement.class));
        }

        when(driver.findElements(By.className(POPULAR_COMPANIES_CLASS_NAME))).thenReturn(companyWebElements);

        return driver;
    }
}
