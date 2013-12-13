Html Elements framework
=======================

This framework is designed to provide easy-to-use way of interaction with web-page elements in your tests. It may be 
considered as an extension of WebDriver Page Object.<br/>
With the help of Html Elements framework you can group web-page elements into blocks, encapsulate logic of interaction with them 
and then easily use created blocks in page objects. It also provides a set of helpful matchers to use with web-page elements 
and blocks. See [JavaDocs](https://oss.sonatype.org/service/local/repositories/releases/archive/ru/yandex/qatools/htmlelements/htmlelements/1.10/htmlelements-1.10-javadoc.jar/!/index.html) 
and [Samples](https://github.com/yandex-qatools/htmlelements-junit-example) for more details.

Other Languages
-------------
In case you are non-java guy, don't panic, there are still few options:

* [Html Elements .NET implementation](https://github.com/yandex-qatools/htmlelements-dotnet) - .NET port made by [Vadzim Hushchanskou](https://github.com/HardNorth)
* [Html Elements PHP implementation](https://github.com/aik099/qa-tools) - PHP port made by [Alexander Obuhovich](https://github.com/aik099)
* [bumblebee framework (C#)](https://github.com/patrickherrmann/Bumblebee) - same idea, nice implementation

Release Notes
-------------
* [Version 1.11 Release Notes](https://github.com/yandex-qatools/htmlelements/blob/master/releasenotes/1.11-releasenotes.ru.md)
* [Version 1.10 Release Notes](https://github.com/yandex-qatools/htmlelements/blob/master/releasenotes/1.10-releasenotes.ru.md)
* [Version 1.9 Release Notes](https://github.com/yandex-qatools/htmlelements/blob/master/releasenotes/1.9-releasenotes.ru.md)

Include Html Elements in your project
-------------------------------------
Maven dependencies for Html Elements core:

```xml
<dependency>
    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-java</artifactId>
    <version>1.11</version>
</dependency>
```

And for Html Elements matchers:

```xml
<dependency>
    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-matchers</artifactId>
    <version>1.11</version>
</dependency>
```

And for Thucydides integration:

```xml
<dependency>
    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-thucydides</artifactId>
    <version>1.11</version>
</dependency>
```

Or you can include both modules at once if needed:

```xml
<dependency>
    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-all</artifactId>
    <version>1.11</version>
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
