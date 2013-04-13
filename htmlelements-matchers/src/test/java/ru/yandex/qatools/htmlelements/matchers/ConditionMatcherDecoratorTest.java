package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 06.03.13
 */
@RunWith(MockitoJUnitRunner.class)
public class ConditionMatcherDecoratorTest {
    @Mock
    private Matcher<Object> condition;

    private final Object itemToMatchCondition = new Object();

    @Mock
    private Matcher<Object> matcher;

    private final Object arbitraryObject = new Object();

    @Test
    public void conditionShouldBeCheckedWhenDecoratedMatcherIsCalled() {
        when(matcher.matches(arbitraryObject)).thenReturn(true);
        when(condition.matches(itemToMatchCondition)).thenReturn(true);

        assertThat(arbitraryObject, should(matcher).inCase(itemToMatchCondition, condition));

        verify(condition).matches(itemToMatchCondition);
    }

    @Test
    public void matcherShouldBeCheckedAfterConditionIfConditionIsSatisfied() {
        when(condition.matches(itemToMatchCondition)).thenReturn(true);

        Matcher<Object> decoratedMatcher = should(matcher).inCase(itemToMatchCondition, condition);
        decoratedMatcher.matches(arbitraryObject);

        InOrder inOrder = inOrder(condition, matcher);
        inOrder.verify(condition).matches(itemToMatchCondition);
        inOrder.verify(matcher).matches(arbitraryObject);
    }

    @Test
    public void satisfiedConditionShouldNotChangeMatcherBehaviour() {
        when(condition.matches(itemToMatchCondition)).thenReturn(true);

        Matcher<Object> decoratedMatcher = should(matcher).inCase(itemToMatchCondition, condition);

        when(matcher.matches(arbitraryObject)).thenReturn(true);
        assertThat(decoratedMatcher.matches(arbitraryObject), equalTo(matcher.matches(arbitraryObject)));

        when(matcher.matches(arbitraryObject)).thenReturn(false);
        assertThat(decoratedMatcher.matches(arbitraryObject), equalTo(matcher.matches(arbitraryObject)));
    }
}
