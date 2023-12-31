package de.hdmstuttgart.wetter.weather

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.hdmstuttgart.wetter.MainActivity
import de.hdmstuttgart.wetter.R

/** The weather fragment holds an XML-Constraint-Layout which contains the
 * FragmentContainerView with the name de.hdmstuttgart.wetter.weather.WeatherFragment.
 * So the GUI-Components plus the logic to display the weather data for
 * the certain town the user searched for in the SearchFragment
 * rest in the Weather Fragment.
 * */
class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // Check if the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we add the
        // WeatherFragment dynamically.
        if (savedInstanceState == null) {
            val fragment = WeatherFragment()
            val townName = intent.getStringExtra("townName")
            val bundle = Bundle()
            bundle.putString("townName", townName)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                fragment
            ).commit()
        }
    }
    fun NavigateToMainFragment(view: View) {
        //Navigates to the main fragment.
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}