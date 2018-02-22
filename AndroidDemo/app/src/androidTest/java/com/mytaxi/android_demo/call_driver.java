package com.mytaxi.android_demo;

import com.mytaxi.android_demo.activities.MainActivity;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class call_driver {

    //region Login Test Data
    private static final String LOGIN_USERNAME = "whiteelephant261";
    private static final String LOGIN_PASSWORD = "video";
    private static final String LOGIN_BUTTON_LABEL = "Login";
    //endregion

    //region Search Test Data
    private static final String SEARCH_INPUT = "sa";
    private static final String SEARCH_DRIVER = "Sarah Friedrich";
    //endregion

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void call_driver() {
        /**
         * Hi! I'm Alex and I am new to Android Studio & Espresso
         * In call driver test just the main scenario of calling a user was implemented
         * Because of time reasons :) didn't implemented a test that will start the app
         * Neither a wait before clicking on the autocompletion results
         * But I have created an assertion to see that I have been redirected to the right screen
         * of the driver
        * */

        //login to the application
        ViewInteraction loginUsernameEditText = onView(withId(R.id.edt_username));
        loginUsernameEditText.perform(typeText(LOGIN_USERNAME), closeSoftKeyboard());

        ViewInteraction loginPasswordEditText = onView(withId(R.id.edt_password));
        loginPasswordEditText.perform(typeText(LOGIN_PASSWORD), closeSoftKeyboard());

        ViewInteraction loginButton = onView(allOf(withId(R.id.btn_login), withText(LOGIN_BUTTON_LABEL)));
        loginButton.perform(click());

        //search for user
        ViewInteraction searchTextView = onView(allOf(withId(R.id.textSearch), isDisplayed()));
        searchTextView.perform(typeText(SEARCH_INPUT), closeSoftKeyboard());

        onView(withText(SEARCH_DRIVER))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        //verify that redirection works properly
        ViewInteraction textView = onView(withId(R.id.textViewDriverName));
        textView.check(matches(withText(SEARCH_DRIVER)));

        //call driver
        ViewInteraction callButton = onView(withId(R.id.fab));
        callButton.perform(click());
    }
}

