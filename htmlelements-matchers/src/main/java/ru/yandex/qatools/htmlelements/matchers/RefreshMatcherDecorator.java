package ru.yandex.qatools.htmlelements.matchers;

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
    private Matcher<? extends T> matcher;

    private boolean isPrerefresh = true;

    @Override
    protected boolean matchesSafely(T item) {
        if (isPrerefresh) {
            driver.navigate().refresh();
        }
        try {
            return matcher.matches(item);
        } finally {
            if(!isPrerefresh) {
                driver.navigate().refresh();
            }
        }
    }

    public RefreshMatcherDecorator(Matcher<? extends T> matcher, WebDriver driver) {
        this.driver = driver;
        this.matcher = matcher;
    }

    private RefreshMatcherDecorator<T> postrefresh() {
        isPrerefresh = false;
        return this;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(isPrerefresh ? "(after refresh) " : "").appendDescriptionOf(matcher);

    }


    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }


    @Factory
    public static <T> Matcher<T> withPrerefresh(Matcher<? extends T> matcher, WebDriver driver) {
        return new RefreshMatcherDecorator<T>(matcher, driver);
    }


    @Factory
    public static <T> Matcher<T> withPostrefresh(Matcher<? extends T> matcher, WebDriver driver) {
        return new RefreshMatcherDecorator<T>(matcher, driver).postrefresh();
    }


}
