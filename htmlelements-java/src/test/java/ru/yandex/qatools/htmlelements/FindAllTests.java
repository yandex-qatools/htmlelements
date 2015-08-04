package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testpages.FindAllPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Murzinov Ilya, murz42@gmail.com
 *         Date: 29.04.14
 */
@RunWith(Parameterized.class)
public class FindAllTests {
    private static WebDriver driver = FindAllPage.mockDriver();

    private FindAllPage page;

    public FindAllTests(FindAllPage page) {
        this.page = page;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        FindAllPage cratedPage = HtmlElementLoader.create(FindAllPage.class, driver);

        FindAllPage populatedPage = new FindAllPage();
        HtmlElementLoader.populate(populatedPage, driver);

        return Arrays.asList(new Object[][]{{cratedPage}, {populatedPage}});
    }

    @Test
    public void webElementFieldShouldNotBeNullWhenUsingFindAll() {
        assertThat("WebElement field should be not null after initialization when using FindAll",
                page.getFindAllElement(), is(notNullValue()));
    }

    @Test
    public void htmlElementFieldShouldNotBeNullWhenUsingFindAll() {
        assertThat("HtmlElement field should be not null after initialization when using FindAll",
                page.getFindAllHtmlElement(), is(notNullValue()));
    }

    @Test
    public void htmlElementFieldShouldNotBeNullWhenUsingFindAllWithMultipleFindBy() {
        assertThat("HtmlElement field should be not null after initialization when using FindAll with multiple FindBy's",
                page.getFindAllMultipleByHtmlElement(), is(notNullValue()));
    }

    @Test
    public void webElementListShouldHaveCorrectSizeWhenUsingFindAll() {
        assertThat("List<WebElement> should have 3 items when using FindAll",
                page.getFindAllWebElementList().size(), is(equalTo(3)));
    }

    @Test
    public void webElementListShouldHaveCorrectSizeWhenUsingFindAllWithMultipleBy() {
        assertThat("List<WebElement> should have 6 items when using FindAll with two FindBy's",
                page.getFindAllMultipleByWebElementList().size(), is(equalTo(6)));
    }
}
