package ru.yandex.qatools.htmlelements.matchers;


import org.hamcrest.Matcher;
import ru.yandex.qatools.htmlelements.matchers.decorators.Action;
import ru.yandex.qatools.htmlelements.matchers.decorators.ActionMatcherDecorator;
import ru.yandex.qatools.htmlelements.matchers.decorators.Condition;
import ru.yandex.qatools.htmlelements.matchers.decorators.ConditionMatcherDecorator;

import static ru.yandex.qatools.htmlelements.matchers.decorators.ActionMatcherDecorator.decorateMatcherWithAction;
import static ru.yandex.qatools.htmlelements.matchers.decorators.ConditionMatcherDecorator.decorateMatcherWithCondition;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 28.02.13
 */
public class MatcherDecoratorFactory<T> {
    private final Matcher<? super T> matcher;

    public static <T> MatcherDecoratorFactory<T> should(Matcher<? super T> matcher) {
        return new MatcherDecoratorFactory<T>(matcher);
    }

    private MatcherDecoratorFactory(Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    public Matcher<T> after(Action action) {
        return decorateMatcherWithAction(matcher, action);
    }

    public Matcher<T> inCase(Condition condition) {
        return decorateMatcherWithCondition(matcher, condition);
    }
}
