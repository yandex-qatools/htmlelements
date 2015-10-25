package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 06.03.13
 */
@RunWith(MockitoJUnitRunner.class)
public class NotSatisfiedConditionMatcherDecoratorTest {
    @Mock
    private Matcher<Object> condition;

    private final Object itemToMatchCondition = new Object();

    @Mock
    private Matcher<Object> matcher;

    private final Object arbitraryObject = new Object();

    @Rule
    public TestRule testRule = (base, description) -> new Statement() {
        @Override
        public void evaluate() throws Throwable {
            try {
                base.evaluate();
                fail();
            } catch (AssumptionViolatedException e) {
                // Skip
            }
        }
    };

    @Test
    public void notSatisfiedConditionShouldCauseAssumptionViolatedException() {
        when(condition.matches(itemToMatchCondition)).thenReturn(false);

        assertThat(arbitraryObject, should(matcher).inCase(itemToMatchCondition, condition));
    }
}
