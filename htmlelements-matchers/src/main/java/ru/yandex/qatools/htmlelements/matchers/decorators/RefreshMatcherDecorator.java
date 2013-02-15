package ru.yandex.qatools.htmlelements.matchers.decorators;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 08.02.13
 * Time: 17:37
 */
public class RefreshMatcherDecorator<T> extends TypeSafeMatcher<T> {

    private WebDriver driver;
    private Matcher<? super T> matcher;


    @Override
    protected boolean matchesSafely(T item) {
        driver.navigate().refresh();
        return matcher.matches(item);
    }

    public RefreshMatcherDecorator(Matcher<? super T> matcher, WebDriver driver) {
        this.driver = driver;
        this.matcher = matcher;
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("(after refresh) ").appendDescriptionOf(matcher);

    }


    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }


    @Factory
    public static <T> Matcher<T> withPrerefresh(Matcher<? super T> matcher, WebDriver driver) {
        return new RefreshMatcherDecorator<T>(matcher, driver);
    }

}
