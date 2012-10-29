HtmlElements library
====================

This library is designed to provide easy-to-use way of interaction with web-page elements in your tests. It may be 
considered as an extension of WebDriver Page Object.

Include HtmlElements in your project
------------------------------------
Maven dependencies:

    <dependency>
        <groupId>ru.yandex.qatools.htmlelements</groupId>
        <artifactId>htmlelements-java</artifactId>
        <version>1.8</version>
    </dependency>

Create blocks of elements
-------------------------
For example, let's create a block for the search form on the page http://www.yandex.com:

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

Construct page object using created blocks
------------------------------------------
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
Created page objects can be used in your tests. That makes tests more comprehensive and easy to write.

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