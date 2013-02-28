package ru.yandex.qatools.htmlelements.matchers.decorators;


import org.hamcrest.Matcher;

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
        return new ActionMatcherDecorator<T>(matcher, action);
    }
}
