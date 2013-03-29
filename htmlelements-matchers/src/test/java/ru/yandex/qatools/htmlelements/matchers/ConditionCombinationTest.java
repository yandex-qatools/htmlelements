package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.junit.Test;
import ru.yandex.qatools.htmlelements.matchers.decorators.Condition;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 29.03.13
 */
public class ConditionCombinationTest {
    private final Condition satisfiedCondition = new Condition() {
        @Override
        public boolean isSatisfied() {
            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("satisfied condition");
        }
    };

    private final Condition unsatisfiedCondition = new Condition() {
        @Override
        public boolean isSatisfied() {
            return false;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("unsatisfied condition");
        }
    };

    @Test
    public void conjunctionOfSatisfiedConditionsShouldProduceSatisfiedCondition() {
        Condition conjunction = satisfiedCondition.and(satisfiedCondition);
        assertThat(conjunction.isSatisfied(), equalTo(true));
    }

    @Test
    public void conjunctionOfSatisfiedAndUnsatisfiedConditionsShouldProduceUnsatisfiedCondition() {
        Condition conjunction = satisfiedCondition.and(unsatisfiedCondition);
        assertThat(conjunction.isSatisfied(), equalTo(false));
    }

    @Test
    public void disjunctionOfSatisfiedConditionsShouldProduceSatisfiedCondition() {
        Condition disjunction = satisfiedCondition.or(satisfiedCondition);
        assertThat(disjunction.isSatisfied(), equalTo(true));
    }

    @Test
    public void disjunctionOfUnsatisfiedAndSatisfiedConditionsShouldProduceSatisfiedCondition() {
        Condition disjunction = unsatisfiedCondition.or(satisfiedCondition);
        assertThat(disjunction.isSatisfied(), equalTo(true));
    }

    @Test
    public void disjunctionOfUnsatisfiedConditionsShouldProduceUnsatisfiedCondition() {
        Condition disjunction = unsatisfiedCondition.or(unsatisfiedCondition);
        assertThat(disjunction.isSatisfied(), equalTo(false));
    }
}