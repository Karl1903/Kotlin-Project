package de.hdmstuttgart.wetter.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.weather.WeatherActivity

/** The Search Fragment holds an XML-Constraint-Layout which contains the
 * FragmentContainerView with the name de.hdmstuttgart.wetter.search.SearchFragment.
 * So the GUI-Components plus the logic for searching the town the user wants
 * to get the weather for are embedded in the Search Fragment.
 * */
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    //Navigates to the weather activity.
    fun onWeatherTownClick(view: View){
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
    }
}