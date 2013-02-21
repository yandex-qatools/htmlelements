package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.yandex.qatools.htmlelements.matchers.decorators.Condition;
import ru.yandex.qatools.htmlelements.matchers.decorators.TimeoutCondition;

import static com.google.common.base.Joiner.on;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static ru.yandex.qatools.htmlelements.matchers.decorators.WaitForMatcherDecorator.withWaitFor;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 10.01.13
 * Time: 19:39
 */
@RunWith(MockitoJUnitRunner.class)
public class WaitForMatcherTest {

    public static final Object ANY_OBJECT = "";
    
    private TimeoutCondition twoSecondsNotExpired = new TimeoutCondition(SECONDS.toMillis(2));

    @Mock
    private Matcher<Object> matcher;

    @Test(expected = AssertionError.class)
    public void decoratorShouldThrowAssertionException() {
        assertThat(true, withWaitFor(equalTo(false)).withTimeout(SECONDS.toMillis(2)));
    }

    @Test
    public void decoratorShouldTryWhileGetTrue() throws Exception {
        when(matcher.matches(any())).thenReturn(false, false, true);

        assertThat(new Object(), withWaitFor(matcher));
        verify(matcher, times(3)).matches(any());
    }

    @Test
    public void decShouldTryWhileTimerGoesOut() throws Exception {
        when(matcher.matches(any())).thenReturn(false);

        Boolean result = withWaitFor(matcher).
        					withTimeout(SECONDS.toMillis(2)).
        					withInterval(MILLISECONDS.toMillis(250)).matches(ANY_OBJECT);
        // 8 for check + return = 9
        verify(matcher, times(9)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void orUntilAddsAdditionalCondition() {
        when(matcher.matches(any())).thenReturn(false);
        
        twoSecondsNotExpired.start();
        Boolean result = withWaitFor(matcher).andWhile(twoSecondsNotExpired).matches(ANY_OBJECT);
        
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void withTimeoutOverridesDefaultTimeout() {
        when(matcher.matches(any())).thenReturn(false);
        
        Boolean result = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1)).matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void withTimeoutShouldNotOverrideCondition() {
        when(matcher.matches(any())).thenReturn(false);
        
        twoSecondsNotExpired.start();
        Boolean result = withWaitFor(matcher).andWhile(twoSecondsNotExpired).withTimeout(SECONDS.toMillis(5)).matches(ANY_OBJECT);
        verify(matcher, times(5)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void timeoutConditionShouldNotStartImmediately() throws InterruptedException {
        when(matcher.matches(any())).thenReturn(false);
        
        Matcher<? super Object> withWaitForMatcher = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1));
        Thread.sleep(SECONDS.toMillis(2));
        Boolean result = withWaitForMatcher.matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }
    
    @Test
    public void allConditionsShouldBeChecked() {
        when(matcher.matches(any())).thenReturn(false);
        
        TimeoutCondition oneSecondNotExpired = new TimeoutCondition(SECONDS.toMillis(1));
        oneSecondNotExpired.start();
        twoSecondsNotExpired.start();
        
        Boolean result = withWaitFor(matcher).andWhile(oneSecondNotExpired).andWhile(twoSecondsNotExpired).matches(ANY_OBJECT);
        verify(matcher, times(3)).matches(ANY_OBJECT);
        assertThat("Miracle! False now is true!", result, is(false));
    }


    @Test
    public void descriptionShouldContainAllConditionDescriptions() throws Exception {
        long timeOut = SECONDS.toMillis(15);

        String descriptionOne = "When the hell will come";
        String descriptionTwo = "When developers will program without bugs";
        String descriptionOfWaiter = "While waiting [<" + timeOut + "L>] millis";

        String or = " or ";

        Description description = new StringDescription();

        withWaitFor(is(false))
                .andWhile(describedCondition(descriptionOne))
                .andWhile(describedCondition(descriptionTwo))
                .withTimeout(timeOut).describeTo(description);

        assertThat(description.toString(),
                startsWith(on(or).join(descriptionOfWaiter, descriptionOne, descriptionTwo)));
    }

    private Condition describedCondition(final String stringDescription) {
        return new Condition() {
            @Override
            public boolean isTrue() {
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(stringDescription);
            }
        };
    }
}
