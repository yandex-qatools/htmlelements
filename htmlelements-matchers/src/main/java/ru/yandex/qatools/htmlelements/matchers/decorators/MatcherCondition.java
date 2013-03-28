package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 03.03.13
 */
public class MatcherCondition<T> extends Condition {
    private final Matcher<? super T> matcher;
    private final T item;

    public MatcherCondition(Matcher<? super T> matcher, T item) {
        this.matcher = matcher;
        this.item = item;
    }

    @Override
    public boolean isSatisfied() {
        return matcher.matches(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher);
    }
}
