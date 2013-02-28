package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.SelfDescribing;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public interface Action extends SelfDescribing {
    void perform();
}
