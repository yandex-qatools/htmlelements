Использование типизированных элементов
======================================

При тестировании веб-интерфейсов приходится взаимодействовать со монжеством различных элементов на страницах.
В Selenium WebDriver взаимодействие с любым элементом происходит при помощи универсального интерфейса
[`WebElement`](http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/WebElement.html), который 
сожержит большое количество методов для взаимодействия с элементами разного типа. Однако, пользоваться им далеко не 
всегда удобно: 
* Например, у этого интерфейса есть метод `sendKeys(CharSequence... keysToSend)` для того, чтобы 
напечатать текст. Но, если вы работаете с ссылкой или с кнопкой, этот метод вам совсем не понадобятся.
* Кроме этого, часто требуется взаимодействовать с более сложными элементами – группами radio-button'ов, выпадающими 
списками и т. д. Для таких элементов функциональности интерфейса `WebElement` недостаточно.

Чтобы сделать работу с разнообразными элементами на страницах удобной, мы реализовали в нашем фреймворке концепцию 
типизированных элементов.

Использование готовых типизированных элементов
----------------------------------------------
Давайте возьмем уже знакомое нам из раздела ["Начало работы"](https://github.com/yandex-qatools/htmlelements/blob/master/htmlelements-samples/docs/samples/gettingstarted.ru.md)
описние поисковой формы главной страницы Яндекса:

    public class SearchArrow extends HtmlElement {    
        
        @FindBy(xpath = ".//input[@class='b-form-input__input']")
        private WebElement requestInput;
    
        @FindBy(xpath = ".//input[@class='b-form-button__input']")
        private WebElement searchButton;
    
        public WebElement getRequestInput() {
            return requestInput;
        }
    
        public WebElement getSearchButton() {
            return searchButton;
        }
    
        public void searchFor(String request) {
            getRequestInput().clear();
            getRequestInput().sendKeys(request);
            getSearchButton().submit();
        }
    }

Здесь поисковая строка и кнопка поиска описаны с помощью интерфейса `WebElement`. Давайте перепишем этот пример с
использованием уже имеющихся в фреймворке типизированных элементов `TextInput` и `Button`:


Написание собственных типизированных элементов
----------------------------------------------




