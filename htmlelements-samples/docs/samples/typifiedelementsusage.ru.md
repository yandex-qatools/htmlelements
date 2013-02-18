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



Написание собственных типизированных элементов
----------------------------------------------




