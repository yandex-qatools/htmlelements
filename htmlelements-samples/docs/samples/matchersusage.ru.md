Использоваие матчеров
=====================

Так уже получилось, что стандартная библиотека матчеров для Selenium довольно примитивна, пл-этому и родилась идея
создать модуль в htmlelements, который включает в себя минимальный набор необходимых матчеров.

Предисловие
-----------

У некторых внимательных людей может возникнуть вопрос: "зачем мы используем ДВА набора матчеров?".
Дело в том, что сначала мы реализовали матчеры для WebElements (и HtmlElement) -
[WebElementMatchers](/yandex-qatools/htmlelements/blob/master/htmlelements-matchers/src/main/java/ru/yandex/qatools/htmlelements/matchers/WebElementMatchers.java)
Но для типизированных элементов эти матчеры не подходят, так как
[TypifiedElement](/yandex-qatools/htmlelements/blob/master/htmlelements-java/src/main/java/ru/yandex/qatools/htmlelements/element/TypifiedElement.java)
не имплементирует интерфейс `WebElement`, но имлементирует полезный интерфейс `WrapsElement` c единственным методом `getWrappedElement()`.
Чтобы упростить конструкцию:

    assertThat(link.getWrappedElement(), hasText("Some text"))

Мы сделали матчер-обертку основной задачей которого является получение доступа к `wrappedElement`.
Так образом появился еще один набор матчеров для типизированных элементов
[WrapsElementMatchers](/yandex-qatools/htmlelements/blob/master/htmlelements-matchers/src/main/java/ru/yandex/qatools/htmlelements/matchers/WrapsElementMatchers.java).

Соответственно для WebElement-ов необходимо импортить класс:

    @import ru.yandex.qatools.htmlelements.matchers.WebElementMatchers

а для TypifiedElement-ов соответственно:

    @import ru.yandex.qatools.htmlelements.matchers.WrpasElementMatchers

Довольно запутанная схема получилась:( У нас есть идеи как это можно расшить, но руки пока недоходят.

Зависимости
-----------

Если у вас в зависимостях использутей `htmlelements-all`, то ничего делать не надо.
Иначе необходимо подключить зависимости:

    <groupId>ru.yandex.qatools.htmlelements</groupId>
    <artifactId>htmlelements-matchers</artifactId>
	<version>1.9-SNAPSHOT</version>

Does Element Exist Matcher
--------------------------

Класс: [DoesElementExistMatcher](/yandex-qatools/htmlelements/blob/master/htmlelements-matchers/src/main/java/ru/yandex/qatools/htmlelements/matchers/DoesElementExist.java)

Один из самых полезных матчеров. Его задача - проверить, что элемент присутствует на странице.
По сути, происходит обращение к элементу и в случае возникновения эксепшена возвращается `false`, иначе `true`.

    assertThat(element, exist());

Так как страницы часто содержат ajax-овую логику, то этот матчер удобно использовать в связке с WaitForMatcher:

    assertThat(element, withWaitFor(exist()))

Is Element Displayed Matcher
----------------------------

Класс: [IsElementDisplayedMatcher](/yandex-qatools/htmlelements/blob/master/htmlelements-matchers/src/main/java/ru/yandex/qatools/htmlelements/matchers/IsElementDisplayedMatcher.java)

Проверяет





