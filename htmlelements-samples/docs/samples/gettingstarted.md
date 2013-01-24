Начало работы
=============

Подключение зависимостей
------------------------

Чтобы использовать библиотеку HtmlElements необходимо создать простой [maven-проект](http://maven.apache.org/guides/getting-started/index.html).
После этого добавить в зависимости свежую версию библиотеки:

    <dependency>
        <groupId>ru.yandex.qatools.htmlelements</groupId>
        <artifactId>htmlelements-matchers</artifactId>
        <version>1.9</version>
    </dependency>

Выполните команду mvn clean compile, чтобы проерить, что ваш проект компилируется.

Подготовка эелемнтов и страниц
------------------------------

В качестве примера возьмем заглавную страницу википедии (http://www.yandex.ru).
Давайте опишем для начала простенький элемент, например поисковую строку:

    @Block(@FindBy(className = "b-morda-search-form"))
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

На этом примере видно, что помимо элементов блок содержит логику взаимодействия с ним 'searchFor(String request)'.
