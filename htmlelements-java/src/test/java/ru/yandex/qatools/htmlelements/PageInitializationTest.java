package ru.yandex.qatools.htmlelements;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import ru.yandex.qatools.htmlelements.testpages.AllElementTypesPage;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 13.08.12
 */
@RunWith(Parameterized.class)
public class PageInitializationTest {
    private static WebDriver driver = AllElementTypesPage.mockDriver();

    private AllElementTypesPage page;

    public PageInitializationTest(AllElementTypesPage page) {
        this.page = page;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        AllElementTypesPage cratedPage = HtmlElementLoader.create(AllElementTypesPage.class, driver);

        AllElementTypesPage populatedPage = new AllElementTypesPage();
        HtmlElementLoader.populate(populatedPage, driver);

        return Arrays.asList(new Object[][]{{cratedPage}, {populatedPage}});
    }

    @Test
    public void webElementFieldShouldNotBeNull() {
        assertThat("WebElement field should be not null after initialization",
                page.getElement(), is(notNullValue()));
    }

    @Test
    public void htmlElementFieldShouldNotBeNull() {
        assertThat("HtmlElement field should be not null after initialization",
                page.getHtmlElement(), is(notNullValue()));
    }

    @Test
    public void textInputFieldShouldNotBeNull() {
        assertThat("TextInput field should be not null after initialization",
                page.getTextInput(), is(notNullValue()));
    }

    @Test
    public void buttonFieldShouldNotBeNull() {
        assertThat("Button field should be not null after initialization",
                page.getButton(), is(notNullValue()));
    }

    @Test
    public void radioFieldShouldNotBeNull() {
        assertThat("Radio field should be not null after initialization",
                page.getRadio(), is(notNullValue()));
    }

    @Test
    public void radioShouldHaveCorrectButtonsNumber() {
        assertThat("Radio should have 3 buttons after initialization",
                page.getRadio().getButtons().size(), is(equalTo(3)));
    }

    @Test
    public void textInputListFiledShouldNotBeNull() {
        assertThat("List<TextInput> field should be not null after initialization",
                page.getTextInputList(), is(notNullValue()));
    }

    @Test
    public void textInputListShouldHaveCorrectSize() {
        assertThat("List<TextInput> should have 3 items",
                page.getTextInputList().size(), is(equalTo(3)));
    }

    @Test
    public void htmlElementListFiledShouldNotBeNull() {
        assertThat("List<HtmlElement> field should be not null after initialization",
                page.getHtmlElementList(), is(notNullValue()));
    }

    @Test
    public void htmlElementListShouldHaveCorrectSize() {
        assertThat("List<HtmlElement> should have 3 items",
                page.getHtmlElementList().size(), is(equalTo(3)));
    }

    @Test
    public void webElementListFiledShouldNotBeNull() {
        assertThat("List<WebElement> field should be not null after initialization",
                page.getWebElementList(), is(notNullValue()));
    }

    @Test
    public void webElementListShouldHaveCorrectSize() {
        assertThat("List<WebElement> should have 3 items",
                page.getHtmlElementList().size(), is(equalTo(3)));
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
    public void webElementListShouldHaveCorrectSizeWhenUsingFindAll() {
        assertThat("List<WebElement> should have 3 items when using FindAll",
                page.getFindAllWebElementList().size(), is(equalTo(3)));
    }
}
