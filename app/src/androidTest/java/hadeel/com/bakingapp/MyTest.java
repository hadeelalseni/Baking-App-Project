package hadeel.com.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/*import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;*/
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MyTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void test1(){
        onView(withId(R.id.homeBtn)).perform(click());
    }
    //@Rule
    //public ActivityTestRule<DetailActivity> activityTestRule2 = new ActivityTestRule<>(DetailActivity.class);
    @Before
    public void mainFragment(){
        /*DetailFragment detailFragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putString("Test", "Test");
        detailFragment.setArguments(b);
        activityTestRule2.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_fragment_container, detailFragment)
                .commit();*/

        MainFragment mainFragment = new MainFragment();
        Bundle b = new Bundle();
        b.putString("Hadeel", "Hadeel");
        mainFragment.setArguments(b);
        activityTestRule.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentMain, mainFragment)
                .commit();
    }
    @Test
    public void userCanClickOnRecipeBtn(){
        onView(withId(R.id.main_btn)).perform(click());
    }
}
