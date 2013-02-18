Использование типизированных элементов
======================================

При тестировании веб-интерфейсов приходится взаимодействовать со монжеством различных элементов на страницах.
В Selenium WebDriver взаимодействие с любым элементом происходит при помощи универсального интерфейса
[`WebElement`](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html), который 
сожержит большое количество методов для взаимодействия с элементами разного типа. Однако, пользоваться им далеко не 
всегда удобно: 
* Например, у этого интерфейса есть метод `sendKeys(CharSequence... keysToSend)` для того, чтобы 
напечатать текст. Но, если вы работаете с ссылкой или с кнопкой, этот метод вам совсем не понадобится.
* Кроме этого, часто требуется взаимодействовать с более сложными элементами – группами radio-button'ов, таблицами, 
выпадающими списками и т. д. Для таких элементов функциональности интерфейса `WebElement` недостаточно.

Чтобы сделать работу с разнообразными элементами на страницах удобной, мы реализовали в нашем фреймворке концепцию 
типизированных элементов.

Использование готовых типизированных элементов
----------------------------------------------
Давайте возьмем уже знакомое нам из раздела ["Начало работы"](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-samples/docs/samples/gettingstarted.ru.md)
описние поисковой формы главной страницы Яндекса:

```java
public class SearchArrow extends HtmlElement {

    @FindBy(xpath = ".//input[@class='b-form-input__input']")
    private TextInput requestInput;

    @FindBy(xpath = ".//input[@class='b-form-button__input']")
    private Button searchButton;

    public TextInput getRequestInput() {
        return requestInput;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void searchFor(String request) {
        getRequestInput().clear();
        getRequestInput().sendKeys(request);
        getSearchButton().click();
    }
}
```

Здесь поисковая строка и кнопка поиска описаны с помощью уже имеющихся в фреймворке типизированных элементов 
`TextInput` и `Button`. Они имеют только необходимую функциональность, соответсвующую тем действиям, которые можно 
выполнить с элементами данного типа.

В фреймворке на данный момент реализован следующий набор типизированных элементов:
* [TextInput](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/TextInput.java)
* [Button](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/Button.java)
* [CheckBox](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/CheckBox.java)
* [Link](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/Link.java)
* [Radio](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/Radio.java)
* [Select](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/Select.java)

Посмотрев на интерфейсы таких типизированных элементов, как `TextInput`, `Button` или `Link`, читатель может подумать: 
"Зачем использовать типизированные элементы? Ведь это всего лишь урезанные версии интерфейса WebElement, ведь гораздо 
лучше использовать его". Но все не так просто, как может показаться на первый взгляд.

Давайте взглянем на [страницу расширенного поиска Яндекса](http://yandex.ru/search/advanced?text=test&numdoc=10&lr=2). 
Помимо прочих элементов, на этой странице есть кнопки-переключатели, которые по факту являются группами radio-button'ов.
Если описывать форму расширенного поиска без типизированных элементов, то с каждой из таких групп придется работать как 
с `List<WebElement>`:

```java
public class ExtendedSearchForm extends HtmlElement {

    @FindBy(name = "text")
    private WebElement requestInput;

    //...

    @FindBy(name = "zone")
    private List<WebElement> wordsPositionSelect;

    @FindBy(name = "wordforms")
    private List<WebElement> wordsFormSelect;

    //...
}
```

При этом для таких элементов есть свои характерные действия, которые можно с ними выполнить: выбрать кнопку по номеру или 
по значению, получить выбранную в данный момент кнопку и т. д. И в случае работы со списками соответствующие мтоды придется 
реализовывать каждый раз, снова и снова. Это очень неудобно. Гораздо удобнее работать с группами radio-button'ов, как с 
типизированными элементами, у которых все нужные методы уже реализованы:

```java
public class ExtendedSearchForm extends HtmlElement {

    @FindBy(name = "text")
    private TextInput requestInput;

    //...

    @FindBy(name = "zone")
    private Radio wordsPositionSelect;

    @FindBy(name = "wordforms")
    private Radio wordsFormSelect;

    //...
}
```

Та же ситуация возникает и при взаиможействии с другими составными элементами, такими как таблицы, выпадающие списки, 
календари и т.д.

Кроме этого, даже при работе с простыми элементами вроде `TextInput` и `Button`, ипользование типизированных элементов
делает код более наглядным, поскольку в этом случае сразу видно, элемент какого типа описан в блоке или page-объекте.


Написание собственных типизированных элементов
----------------------------------------------

При написании блоков и page-объектов вам может пондобиться добавить свой собственный типизированный элемент или расширить 
уже существующий. Сделать это очень просто. Для этого нужно, соответственно, отнаследоваться от класса 
[`TypifiedElement`](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/TypifiedElement.java)
или от класса типизированного элемента, который вы хотите расширить, и добавить нужную вам функциональность.


Давайте для примера напишем типизированный элемент для выпадающего списка подсказок в поисковой форме на 
[главной странице Яндекса](http://www.yandex.ru):

```java
public class Suggest extends TypifiedElement {

    public Suggest(WebElement wrappedElement) {
        super(wrappedElement);
    }

    private List<WebElement> getItems() {
        return getWrappedElement().findElements(By.xpath("//li"));
    }

    public void selectByIndex(int itemIndex) {
        getItems().get(itemIndex).click();
    }

    public void selectByValue(String itemValue) {
        for (WebElement item : getItems()) {
            if (itemValue.equals(item.getText())) {
                item.click();
                return;
            }
        }
        throw new NoSuchElementException();
    }
    
    //...
}
```

Как видно из примера, нужную функциональность можно достаточно легко реализовать, используя метод `getWrappedElement()`, 
который возвращает `WebElement`, оберткой над которым является типизированный элемент.

Резюме
------

Типизированные элементы позволяют очень удобно взаимодействовать с разнообразными элементами в тестах: вы едионожды 
реализуете требуемую функциональность, а затем многократно используете ее в различных местах.

Вместе с механизмом объединения элементов в блоки типизированные элементы позволяют собирать вам page-объекты как 
конструктор. У вас есть заготовленные детальки (типзированные элементы), из них вы можете собирать блоки, блоки вы можете
комбинировать друг с другом, объединять в более крупные блоки и конструировать из них page-объекты. Это черезвычайно 
удобно.


