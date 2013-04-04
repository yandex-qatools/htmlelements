Html Elements framework
=======================

This framework is designed to provide easy-to-use way of interaction with web-page elements in your tests. It may be 
considered as an extension of WebDriver Page Object.<br/>
With the help of Html Elements framework you can group web-page elements into blocks, encapsulate logic of interaction with them 
and then easily use created blocks in page objects. It also provides a set of helpful matchers to use with web-page elements 
and blocks. See [JavaDocs](https://oss.sonatype.org/service/local/repositories/releases/archive/ru/yandex/qatools/htmlelements/htmlelements/1.9/htmlelements-1.9-javadoc.jar/!/index.html) 
for more details.

Include Html Elements in your project
-------------------------------------
Maven dependencies for Html Elements core:

```xml
<dependency>
    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-java</artifactId>
    <version>1.9</version>
</dependency>
```

And for Html Elements matchers:

```xml
<dependency>
    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-matchers</artifactId>
    <version>1.9</version>
</dependency>
```

Create blocks of elements
-------------------------
For example, let's create a block for the search form on the page http://www.yandex.com:

```java
@Name("Search form")
@Block(@FindBy(xpath = "//form"))
public class SearchArrow extends HtmlElement {
    @Name("Search request input")
    @FindBy(id = "searchInput")
    private TextInput requestInput;

    @Name("Search button")
    @FindBy(className = "b-form-button__input")
    private Button searchButton;

    public void search(String request) {
        requestInput.sendKeys(request);
        searchButton.click();
    }
}
```

Construct page object using created blocks
------------------------------------------
You can easily use created blocks in page objects:

```java
public class SearchPage {
    private SearchArrow searchArrow;
    // Other blocks and elements here

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void search(String request) {
        searchArrow.search(request);
    }

    // Other methods here
}
```

Use page objects in your tests
------------------------------
Created page objects can be used in your tests. That makes tests more comprehensive and easy to write.

```java
public class SampleTest {
    private WebDriver driver = new FirefoxDriver();
    private SearchPage searchPage = new SearchPage(driver);

    @Before
    public void loadPage() {
        driver.get("http://www.yandex.com");
    }

    @Test
    public void sampleTest() {
        searchPage.search("yandex");
        // Some assertion here
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
```
