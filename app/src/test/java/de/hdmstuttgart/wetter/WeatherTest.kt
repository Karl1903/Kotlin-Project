package de.hdmstuttgart.wetter

import android.os.StrictMode
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

class WeatherTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java)

    @Test
    fun weatherSearchTest() {
        try {
            // Disable StrictMode
            //StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

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
                .check(matches(atPosition(0, hasDescendant(withText("Lissabon")))))


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

            onView(withId(R.id.homeRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText("Lissabon")))))

            activityScenarioRule.scenario.close()

            ActivityScenario.launch(MainActivity::class.java)

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

            activityScenarioRule.scenario.close()

            ActivityScenario.launch(MainActivity::class.java)

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            //Button to delete the Town. "@+id/trashIcon".
            onView(atPosition(1, hasDescendant(withId(R.id.trashIcon))))
                .perform(click())

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

            onView(withId(R.id.homeRecyclerView))
                .check(matches(not(hasDescendant(withText("London")))))
        } catch (e: ExceptionInInitializerError) {
            Log.d("test expetion:", "test expetion: ${e.message}")
            e.printStackTrace()
            throw e
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
}
