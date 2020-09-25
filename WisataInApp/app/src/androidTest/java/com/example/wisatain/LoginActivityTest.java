package com.example.wisatain;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.wisatain.Activities.Login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    private String email, password;

    @Rule
    public final ActivityRule<LoginActivity> activityRule = new ActivityRule<>(LoginActivity.class);

    @Before
    public void initValidValue () {
        email = "Tito";
        password = "";
    }

    @Test
    public void loginTest () {

        // Type text and then press the button.
        onView(withId(R.id.letEmail)).perform(typeText("Amrizal"));
        onView(withId(R.id.letKataSandi)).perform(typeText("Amrizal"), closeSoftKeyboard());
        onView(withId(R.id.tvDaftar)).perform(click());

        // Check that the text was changed.
//        onView(withId(R.id.letEmail))
//                .check(matches(withText(email)));
    }
}
