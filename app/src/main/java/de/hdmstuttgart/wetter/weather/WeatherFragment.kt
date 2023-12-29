package de.hdmstuttgart.wetter.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.hdmstuttgart.wetter.API.WeatherApi
import de.hdmstuttgart.wetter.ApiClient
import de.hdmstuttgart.wetter.Configuration
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.TownDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//todo: create a Fragment that shows the weather data for one single town.
// 1.the activity with the fragment is started when the user clicks
// on a town in the list
//of the main activity.

// 2.the activity with the fragment is started when the user clicks on a town in the search list
// in the search fragment.
    class WeatherFragment : Fragment() {

    private lateinit var townNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var iconImageView: ImageView

    private var weatherApi: WeatherApi? = null
    private val apiKey = Configuration.API_KEY

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_weather, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Find views in the layout.
            townNameTextView = view.findViewById(R.id.townNameTextView)
            temperatureTextView = view.findViewById(R.id.temperatureTextView)
            descriptionTextView = view.findViewById(R.id.descriptionTextView)
            iconImageView = view.findViewById(R.id.iconImageView)

            weatherApi = ApiClient.instance?.create(weatherApi!!::class.java)

            loadWeatherData("London")

            val id = arguments?.getInt("id")
            val name = arguments?.getString("name")
            val description = arguments?.getString("description")
            val temperature = arguments?.getString("temperature")

            // Load weather icon using Glide library
          //  Glide.with(requireContext())
          //      .load(getWeatherIconUrl(londonWeather.icon))
          //      .into(weatherIconImageView)
        //}

        // Function to get the URL for the weather icon
       // private fun getWeatherIconUrl(icon: String): String {
       //     return "https://openweathermap.org/img/w/$icon.png"
        }

    private fun loadWeatherData(location: String) {
        weatherApi?.getWeatherData(location, apiKey)
            object : Callback<TownDTO> {
                override fun onResponse(call: Call<TownDTO?>, response: Response<TownDTO?>) {
                    if (response.isSuccessful) {
                        val weatherData: TownDTO? = response.body()
                        if (weatherData != null) {
                            updateUI(weatherData)
                        }
                    } else {
                        showErrorToast()
                    }
                }

                override fun onFailure(call: Call<TownDTO?>, t: Throwable?) {
                    showErrorToast()
                }
            }
    }

    private fun showErrorToast() {
        //Toast.makeText(this@WeatherFragment, "fail", Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(townDTO: TownDTO) {
        townNameTextView.text = townDTO.name
        descriptionTextView.text = townDTO.description
        temperatureTextView.text = "Temperature: ${townDTO.temperature} °C"
        // Load weather icon with Glide.
    }
    }