package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.SelfDescribing;

public interface Condition extends SelfDescribing {
	public boolean isTrue();
}
