package de.hdmstuttgart.wetter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

// The test is ok when there is no town saved to the database at the start.
@LargeTest
class WeatherTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun weatherSearchTest() {
        // Disable StrictMode
        //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        //activityScenarioRule.scenario.close()
        try {
            //ActivityScenario.launch(MainActivity::class.java)

            onView(withId(R.id.searchBtn))
                .perform(click())

            onView(withId(R.id.searchWeatherTownEditText))
                .perform(replaceText("Lissabon"), closeSoftKeyboard())

            onView(withId(R.id.searchWeatherTownButton))
                .perform(click())

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            //Weather Fragment: Town-Name check: "@+id/townNameTextView"

            onView(withId(R.id.townNameTextView))
                .check(matches(withText("Location: Lissabon")))

            //ActivityScenario.launch(MainActivity::class.java)

        //onView(withId(R.id.searchRecyclerView))
        //    .perform(
        //        RecyclerViewActions.actionOnItemAtPosition<MovieAdapter.ViewHolder>(
        //            0,
        //            click()
        //        )
        //    )

        //Button navigate to the Main Fragment. "@+id/materialButton"
        onView(withId(R.id.materialButton))
            .perform(click())

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.homeRecyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("Lissabon")))))

        //activityScenarioRule.scenario.close()

        //ActivityScenario.launch(MainActivity::class.java)

        //Sleep for the duration of the API-Call.
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        //Check if Lissabon is still there.
        onView(withId(R.id.homeRecyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("Lissabon")))))

        //Start new search.
        onView(withId(R.id.searchBtn))
            .perform(click())

        //Search different town.
        onView(withId(R.id.searchWeatherTownEditText))
            .perform(replaceText("London"), closeSoftKeyboard())

        onView(withId(R.id.searchWeatherTownButton))
            .perform(click())

        //Sleep for the duration of the API-Call.
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //Button navigate to the Main Fragment. "@+id/materialButton"
        onView(withId(R.id.materialButton))
            .perform(click())

        //Check if Lissabon is still there.
        onView(withId(R.id.homeRecyclerView))
            .check(matches(atPosition(0, hasDescendant(withText("Lissabon")))))

        //Check if London is at position 1.
        onView(withId(R.id.homeRecyclerView))
            .check(matches(atPosition(1, hasDescendant(withText("London")))))

        //activityScenarioRule.scenario.close()

        //ActivityScenario.launch(MainActivity::class.java)

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //Delete the Town. "@+id/trashIcon".
        //onView(atPosition(1, withId(R.id.trashIcon)))
          //  .perform(click())
            //onView(withId(R.id.homeRecyclerView, atPosition(1, hasDescendant(withId(R.id.trashIcon)))))
              //  .perform(click())

            //Delete the Town. "@+id/trashIcon".
            onView(withId(R.id.homeRecyclerView))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<TownAdapter.ViewHolder>(
                        1,
                        MyViewAction.clickChildViewWithId(R.id.trashIcon)
                    )
                )

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            //Start new search.
            onView(withId(R.id.searchBtn))
                .perform(click())

            //Search different town.
            onView(withId(R.id.searchWeatherTownEditText))
                .perform(replaceText("Detroit"), closeSoftKeyboard())

            onView(withId(R.id.searchWeatherTownButton))
                .perform(click())

            //Button navigate to the Main Fragment. "@+id/materialButton"
            onView(withId(R.id.materialButton))
                .perform(click())

            //Check if Lissabon is still there.
            onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("Lissabon")))))

            //Check if Detroit is at position 1.
            onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(1, hasDescendant(withText("Detroit")))))

        //onView(withId(R.id.homeRecyclerView))
        //    .perform(
        //        RecyclerViewActions.actionOnItemAtPosition<TownAdapter.ViewHolder>(
        //            1,
        //            click()
        //        )
        //    )

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }




        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("test123", "test123: ${e.message}")
        }
    }


    companion object {
        fun atPosition(
            position: Int,
            itemMatcher: Matcher<View?>
        ): BoundedMatcher<View?, RecyclerView> {
            Preconditions.checkNotNull(itemMatcher)
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }
    }

    // Define the ViewAction to click the child view within the
    // RecyclerView item. Click the Trash icon to delete the town.
    object MyViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isEnabled()
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController?, view: View) {
                    val v: View = view.findViewById(id)
                    v.performClick()
                }
            }
        }
    }
}
