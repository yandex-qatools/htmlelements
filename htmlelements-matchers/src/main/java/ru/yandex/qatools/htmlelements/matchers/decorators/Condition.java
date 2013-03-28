package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

/**
 * @author Artem Koshelev artkoshelev@yandex-team.ru
 * @author Alexander Tolmachev starlight@yandex-team.ru
 */
public abstract class Condition implements SelfDescribing {

    public abstract boolean isSatisfied();

    public abstract void describeTo(Description description);

    public Condition or(final Condition otherCondition) {
        final Condition thisCondition = this;
        return new Condition() {
            @Override
            public boolean isSatisfied() {
                return thisCondition.isSatisfied() || otherCondition.isSatisfied();
            }

            @Override
            public void describeTo(Description description) {
                description.appendDescriptionOf(thisCondition).appendText(" or ").appendDescriptionOf(otherCondition);
            }
        };
    }

    public Condition and(final Condition otherCondition) {
        final Condition thisCondition = this;
        return new Condition() {
            @Override
            public boolean isSatisfied() {
                return thisCondition.isSatisfied() && otherCondition.isSatisfied();
            }

            @Override
            public void describeTo(Description description) {
                description.appendDescriptionOf(thisCondition).appendText(" and ").appendDescriptionOf(otherCondition);
            }
        };
    }
}
