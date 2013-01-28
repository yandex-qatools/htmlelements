Начало работы
=============

Подключение зависимостей
------------------------

Чтобы использовать библиотеку HtmlElements необходимо создать простой [maven-проект](http://maven.apache.org/guides/getting-started/index.html).
После этого добавить в зависимости свежую версию библиотеки:

    <dependency>
        <groupId>ru.yandex.qatools.htmlelements</groupId>
        <artifactId>htmlelements-java</artifactId>
        <version>1.9-SNAPSHOT</version>
    </dependency>

Выполните команду `mvn clean compile`, чтобы проерить, что ваш проект компилируется.

Пример использования HtmlElements
---------------------------------

В качестве примера возьмем заглавную страницу Яндекса (http://www.yandex.ru).
Давайте опишем для начала простенький элемент, например поисковую строку:

    public class SearchArrow extends HtmlElement {

        @FindBy(xpath = "//input[@class='b-form-input__input']")
        protected WebElement requestInput;

        @FindBy(xpath = "//input[@class='b-form-button__input']")
        protected WebElement searchButton;

        public WebElement getRequestInput() {
            return this.requestInput;
        }

        public WebElement getSearchButton() {
            return this.searchButton;
        }

        public void searchFor(String request) {
            getRequestInput().clear();
            getRequestInput().sendKeys(request);
            getSearchButton().submit();
        }
    }

Этот класс описывает структуру поисковой строки и логику взаимодействия с ней.
Дальше необходимо создать класс MainPage, который содержит поисковую строку:

    public class MainPage {

        @FindBy(className = "b-morda-search-form")
        protected SearchArrow searchArrow;

        public MainPage(WebDriver driver) {
            HtmlElementLoader.populate(this, driver);
        }

        public SearchArrow getSearchArrow() {
            return searchArrow;
        }

    }

Как видно, в конструкторе вызывается инициализация внутренних эелементов стнаицы MainPage с помощью статического метода:
> public static <T> void populate(T instance, WebDriver driver);
Так как поле 'searchArrow' является наследником HtmlElements, то его инициализация происходит рекурсивно.
Таком образом, инициализируются и внутренние элементы поисковой строки:
 -  protected WebElement requestInput;
 -  protected WebElement searchButton;
Для того, чтобы это проверить создадим еще один класс GettingStarted, в котором создим экземляр нашей страницы:

    public class GettingStarted {

        public WebDriver driver = new FirefoxDriver();

        public final String REQUEST = "test";

        @Test
        public void testOutput() throws Exception {
            driver.get("http://www.yandex.ru");

            MainPage mainPage = new MainPage(driver);
            mainPage.getSearchArrow().searchFor(REQUEST);
            assertThat(driver.getTitle(), withWaitFor(containsString(REQUEST)));
        }

        @After
        public void destroyDriver() {
            driver.quit();
        }
    }

В этом примере видно, что после инициализации самой страницы `MainPage` произошла инициализация внутренних элементов.

Ссыли
-----

В этом примере использовались следующие классы:
 * [SearchArrow](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/elements/SearchArrow.java)
 * [MainPage](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/pages/MainPage.java)
 * [GettingStarted](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/GettingStarted.java)