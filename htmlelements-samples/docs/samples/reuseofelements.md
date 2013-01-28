Переиспользование элементов на других страницах
===============================================

Включение известного элемента на другие страницы
------------------------------------------------

Важным аспектом данного фреймворка является возможность повторного испольхования уже созданных элементов.
Предположим, что у нас уже создан элемент поисковой строки и главной страницы Яндекса из примера [Начала работы](gettingstarted.md).
Давайте создадим тест на проверку количества результатов выдачи по запросу:

    public class ReuseOfElements {

        public WebDriver driver = new HtmlUnitDriver();

        public final String REQUEST = "test";

        public final String ANOTHER_REQUEST = "request";

        public final int SEARCH_REQUEST_COUNT = 10;

        @Before
        public void loadStartPage() {
            driver.get("http://www.yandex.ru");
        }

        @Test
        public void testOutput() throws Exception {

            MainPage mainPage = new MainPage(driver);
            mainPage.getSearchArrow().searchFor(REQUEST);

            SearchPage searchPage = new SearchPage(driver);
            assertThat(searchPage.getResults(), hasSize(SEARCH_REQUEST_COUNT));

            searchPage.getSearchArrow().searchFor(ANOTHER_REQUEST);
            assertThat(searchPage.getResults(), hasSize(SEARCH_REQUEST_COUNT));
        }

        @After
        public void destroyDriver() {
            driver.quit();
        }
    }

В этом случае на страницу `SearchPage` включен элемент 'SearchArrow', причем с переопределенным селектором `@FindBy(className = "b-search__table")`.


Дефолтный селектор для элемента
-------------------------------

Если один и тот же элемент находится на разных страницах по одному и тому же селектору, то его можно определить по дефолту следующим образом:

    @Block(@FindBy(className = "b-domik"))
    public class AuthBlock extends HtmlElement { ... }

При этом совершенно не обязательно задавать селектор при включение элемента на страницу:

    public SearchPage {

        protected AuthBlock authBlock;

        ...
    }

Если же селектор все таки указать, то он переопределит дефолтный.

    public SearchPage {

        @FindBy(className = "some-another-domik")
        protected AuthBlock authBlock;

        ...
    }

Полезные ссылки
---------------

В этом примере использовались следующие классы:
 * [SearchArrow](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/elements/SearchArrow.java)
 * [MainPage](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/pages/MainPage.java)
 * [SearchPage](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/pages/SearchPage.java)
 * [AuthBlock](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/elements/AuthBlock.java)
 * [GettingStarted](/yandex-qatools/htmlelements/blob/master/htmlelements-samples/src/main/java/ru/yandex/qatools/htmlelements/samples/GettingStarted.java)
