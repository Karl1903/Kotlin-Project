package de.hdmstuttgart.wetter.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.hdmstuttgart.wetter.R

    /** The weather fragment holds an XML-Constraint-Layout which contains the
     * FragmentContainerView with the name de.hdmstuttgart.wetter.weather.WeatherFragment.
     * So the GUI-Components plus the logic to display the weather data for
     * the certain town the user clicked on in the list are embedded in the Weather Fragment.
     * */
    class WeatherActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_weather)
        }
    }
