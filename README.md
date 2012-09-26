HtmlElements library
====================

This library is designed to provide easy-to-use way of interaction with web-page elements in your tests. It may be 
considered as extension of WebDriver Page Object.

Include HtmlElements in your project
------------------------------------
Maven dependencies:

    <dependency>
        <groupId>ru.yandex.qatools.htmlelements</groupId>
        <artifactId>htmlelements</artifactId>
        <version>1.8-SNAPSHOT</version>
    </dependency>

Create blocks of elements
-------------------------
For example, let's create block for the search form on page http://www.yandex.com:

    import org.openqa.selenium.support.FindBy;
    import ru.yandex.qatools.htmlelements.annotations.Block;
    import ru.yandex.qatools.htmlelements.element.Button;
    import ru.yandex.qatools.htmlelements.element.HtmlElement;
    import ru.yandex.qatools.htmlelements.element.TextInput;

    @Name("Search form")
    @Block(@FindBy(xpath = "//form"))
    public class SearchArrow extends HtmlElement {
        @Name("Search request input")
        @FindBy(id = "searchInput")
        private TextInput requestInput;

        @Name("Search button")
        @FindBy(className = "b-form-button__input")
        private Button searchButton;

        public TextInput getRequestInput() {
            return requestInput;
        }

        public Button getSearchButton() {
            return searchButton;
        }
    }

Use blocks in your page objects
-------------------------------
You can easily use created blocks in page objects:

    import org.openqa.selenium.WebDriver;
    import ru.yandex.qatools.htmlelements.annotations.Name;
    import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
    import ru.yandex.qatools.htmlelements.testelements.SearchArrow;

    public class SearchPage {

        private SearchArrow searchArrow;

        public SearchPage(WebDriver driver) {
            HtmlElementLoader.populatePageObject(this, driver);
        }

        public void enterSearchRequest(String request) {
            searchArrow.getRequestInput().sendKeys(request);
        }
        
        public void clickSearchButton() {
            searchArrow.getSearchButton().click();
        }        
    }

Use page objects in your tests
------------------------------
After you have created page object you can use it in your tests. It will make them more comprehensive and
very easy to write.

    import org.junit.Before;
    import org.junit.Test;
    import org.openqa.selenium.WebDriver;
    import ru.yandex.qatools.htmlelements.testpages.SearchPage;
        
    public class SampleTest {
        private WebDriver driver = new FirefoxDriver();
        private SearchPage searchPage = new SearchPage(driver);
    
        @Before
        public void loadPage() {
            driver.get("http://www.yandex.com");
        }
    
        @Test
        public void sampleTest() {
            searchPage.enterSearchRequest("yandex");
            searchPage.clickSearchButton();
        }
    }