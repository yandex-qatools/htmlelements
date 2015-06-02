package ru.yandex.qatools.htmlelements.testelements;

import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

public class ParentTimeoutClass extends ClassWithTimeout {
    @Timeout(2)
    private HtmlElement element;
    private List<ClassWithTimeout> listElement;
    private ClassWithTimeout classElement;
    private HtmlElement noTimeout;
}
