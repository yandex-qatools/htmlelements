package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.yandex.qatools.htmlelements.matchers.decorators.Action;

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
public class ActionMatcherDecoratorTest {
    @Mock
    private Action action;
    @Mock
    private Matcher<Object> matcher;

    private final Object arbitraryObject = new Object();

    @Test
    public void actionShouldBePerformedBeforeMatching() {
        when(matcher.matches(arbitraryObject)).thenReturn(true);

        assertThat(arbitraryObject, should(matcher).after(action));

        InOrder inOrder = inOrder(action, matcher);
        inOrder.verify(action).perform();
        inOrder.verify(matcher).matches(arbitraryObject);
    }

    @Test
    public void decoratedMatcherShouldNotChangeBehaviour() {
        Matcher<Object> decoratedMatcher = should(matcher).after(action);

        when(matcher.matches(arbitraryObject)).thenReturn(true);
        assertThat(decoratedMatcher.matches(arbitraryObject), equalTo(matcher.matches(arbitraryObject)));

        when(matcher.matches(arbitraryObject)).thenReturn(false);
        assertThat(decoratedMatcher.matches(arbitraryObject), equalTo(matcher.matches(arbitraryObject)));
    }

//    @Test
//    public void decoratedMatcherDescriptionShouldContainActionDescription() {
//
//    }
}
