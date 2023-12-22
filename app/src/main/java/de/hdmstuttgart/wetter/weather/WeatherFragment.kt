package de.hdmstuttgart.wetter.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.Town

//todo: create a Fragment that shows the weather data for one single town.
//the activity and fragment is started when the user clicks on a town in the list
//of the main activity.

//also the activity and fragment are started when the user clicks on a town in the search list
// in the search fragment.
    class WeatherFragment : Fragment() {

    private lateinit var townNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var temperatureTextView: TextView
    //private lateinit var weatherIconImageView: ImageView

        // Sample data structure representing the weather for London
        //private val londonWeather = Town(
          //  id = 1,
          //  name = "London",
          //  description = "Few clouds",
          //  temp = 280.33,
          //  icon = "02n"
          //                         )

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_weather, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Find views in the layout
            townNameTextView = view.findViewById(R.id.townNameTextView)
            temperatureTextView = view.findViewById(R.id.temperatureTextView)
            descriptionTextView = view.findViewById(R.id.descriptionTextView)
            //weatherIconImageView = view.findViewById(R.id.weatherIconImageView)

            val id = arguments?.getInt("id")
            val name = arguments?.getString("name")
            val description = arguments?.getString("description")
            val temperature = arguments?.getDouble("temperature")

            val townData = Town(id = id, name = name.orEmpty(), description = description.orEmpty(), temp  = temperature)

            updateUI(townData)

            // Set data to views hardcoded for London.
            //townNameTextView.text = londonWeather.name
            //temperatureTextView.text = "${londonWeather.temp} °C"
            //descriptionTextView.text = londonWeather.description

            // Load weather icon using Glide library
          //  Glide.with(requireContext())
          //      .load(getWeatherIconUrl(londonWeather.icon))
          //      .into(weatherIconImageView)
        //}

        // Function to get the URL for the weather icon
       // private fun getWeatherIconUrl(icon: String): String {
       //     return "https://openweathermap.org/img/w/$icon.png"
        }

    private fun updateUI(town: Town) {
        townNameTextView.text = town.name
        descriptionTextView.text = town.description
        temperatureTextView.text = "Temperature: ${town.temp} °C"
    }
    }