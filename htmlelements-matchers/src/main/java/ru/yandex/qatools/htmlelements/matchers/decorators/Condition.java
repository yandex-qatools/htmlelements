package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.SelfDescribing;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 */
public interface Condition extends SelfDescribing {
    boolean isSatisfied();
}
