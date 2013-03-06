package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.yandex.qatools.htmlelements.matchers.decorators.Waiter;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 06.03.13
 */
@RunWith(MockitoJUnitRunner.class)
public class WaiterMatcherDecoratorTest {
    @Mock
    private Waiter waiter;
    @Mock
    private Matcher<Object> matcher;

    private final Object arbitraryObject = new Object();

    @Test
    public void waiterShouldBeStartedAndCheckedBeforeMatching() {
        when(matcher.matches(arbitraryObject)).thenReturn(true);
        when(waiter.shouldStopWaiting()).thenReturn(true);

        assertThat(arbitraryObject, should(matcher).whileWaitingUntil(waiter));

        InOrder inOrder = inOrder(waiter, matcher);
        inOrder.verify(waiter).startWaiting();
        inOrder.verify(waiter).shouldStopWaiting();
        inOrder.verify(matcher).matches(arbitraryObject);
    }

    @Test
    public void waiterShouldNotChangeMatcherBehaviour() {
        Matcher<Object> decoratedMatcher = should(matcher).whileWaitingUntil(waiter);

        when(matcher.matches(arbitraryObject)).thenReturn(true);
        when(waiter.shouldStopWaiting()).thenReturn(true, false);
        assertThat(decoratedMatcher.matches(arbitraryObject), equalTo(matcher.matches(arbitraryObject)));

        when(matcher.matches(arbitraryObject)).thenReturn(false);
        when(waiter.shouldStopWaiting()).thenReturn(true, false);
        assertThat(decoratedMatcher.matches(arbitraryObject), equalTo(matcher.matches(arbitraryObject)));
    }
}
