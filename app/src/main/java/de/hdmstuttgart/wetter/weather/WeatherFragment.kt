package de.hdmstuttgart.wetter.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import de.hdmstuttgart.wetter.API.WeatherApi
import de.hdmstuttgart.wetter.ApiClient
import de.hdmstuttgart.wetter.Configuration
import de.hdmstuttgart.wetter.MainActivity
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.Town
import de.hdmstuttgart.wetter.Town.TownDTO
import de.hdmstuttgart.wetter.Town.TownDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import de.hdmstuttgart.wetter.TownTrackerApplication
import de.hdmstuttgart.wetter.search.SearchActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.Locale


//The Weather Fragment that shows the weather data for one single town
// for the date right now.
// 1.the activity with the fragment is started when the user clicks
// the row with the town in the list in the main fragment.

// 2.the activity with the fragment is started when the user clicks
// the button to search the data for the town in the search Fragment.
class WeatherFragment : Fragment() {

    private lateinit var townNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var windtempoTextView: TextView
    private lateinit var dateTextView: TextView

    private lateinit var iconImageView: ImageView

    private var weatherApi: WeatherApi? = null
    private val apiKey = Configuration.API_KEY

    private var townName: String? = null

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
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        windtempoTextView = view.findViewById(R.id.windtempoTextView)
        iconImageView = view.findViewById(R.id.iconImageView)
        dateTextView = view.findViewById(R.id.dateTextView)

        //weatherApi = ApiClient.instance?.create(weatherApi!!::class.java)

        // the database operations must be executed
        // with the background thread to avoid freezing the UI.
        lifecycleScope.launch(Dispatchers.IO) {

            //get the weather data from the database
            //with the text from the user.
            //we get the text from the searchActivity which gave it to
            //the weatherfragment with the putExtra method.
            // Retrieve the town name from the arguments bundle.
            arguments?.let {
                townName = it.getString("townName")
            }
            Log.d("the townname in the fragment is", "the townname in the fragment is: $townName")
            //Note: that works in the activity. but in the fragment we need to
            //get it from the arguments bundle.
            //townName = intent.getStringExtra("townName")
            val town = townName?.let { it1 -> loadWeatherData(it1) }

            // withContext takes it back to the main thread for UI updates)
            withContext(Dispatchers.Main) {
                // Update UI with the result.
                if (town != null) {
                    updateUI(town)
                }
            }}

        val id = arguments?.getInt("id")
        val name = arguments?.getString("name")
        val description = arguments?.getString("description")
        val temp = arguments?.getString("temp")

        // Load weather icon using Glide library
        //  Glide.with(requireContext())
        //      .load(getWeatherIconUrl(londonWeather.icon))
        //      .into(weatherIconImageView)
        //}

        // Function to get the URL for the weather icon
        // private fun getWeatherIconUrl(icon: String): String {
        //     return "https://openweathermap.org/img/w/$icon.png"


    }

    //private fun loadWeatherData(townName: String) {
    //    weatherApi?.getWeatherData(townName, apiKey)
    //        object : Callback<TownDTO> {
    //            override fun onResponse(call: Call<TownDTO?>, response: Response<TownDTO?>) {
    //                if (response.isSuccessful) {
    //                    val weatherData: Town? = response.body()
    //                    if (weatherData != null) {
    //                        updateUI(weatherData)
    //                    }
    //                } else {
    //                    showErrorToast()
    //                }
    //            }

    //          override fun onFailure(call: Call<TownDTO?>, t: Throwable?) {
    //                showErrorToast()
    //            }
    //        }
    //}

    //the weather data was get in the search request,
    //now we just need to get it from the local storage to show it in the weather fragment.
    private fun loadWeatherData(townName: String): Town {
        var town = Town(key = 0,
            id= "1",
            name = "",
            description = "",
            temperature = "",
            windtempo = "")
        activity?.let { it ->
            val townTrackerApplication = it.application as TownTrackerApplication
            val towns = townTrackerApplication.repository.getTowns()

            for (element in towns){
                if (element.name == townName) {
                    town = element
                }
            }
        }
        return town
    }

    private fun showErrorToast() {
        //Toast.makeText(this@WeatherFragment, "fail", Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(town: Town) {
        val date = LocalDate.now().toString()
        dateTextView.text = "date: $date"
        townNameTextView.text = "Location: ${town.name}"
        descriptionTextView.text = "weather description: ${town.description}"
        temperatureTextView.text = "Temperature: ${town.temperature}"
        windtempoTextView.text = "wind tempo: ${town.windtempo}"
        // get the picture with Glide based on the description.
        val iconResource = getIconResource(town.description)
        Glide.with(requireContext())
            .load(iconResource)
            .into(iconImageView)
    }
    private fun getIconResource(description: String): Int {
        // There are different PNG files for each weather condition.
        return when {
            "rain" in description.lowercase(Locale.ROOT) -> R.drawable.rain
            "clear" in description.lowercase(Locale.ROOT) -> R.drawable.sonnig
            "cloud" in description.lowercase(Locale.ROOT) -> R.drawable.wolken
            "sunny" in description.lowercase(Locale.ROOT) -> R.drawable.sonnig
            "scattered" in description.lowercase(Locale.ROOT) -> R.drawable.teilweisewolken
            "wind" in description.lowercase(Locale.ROOT) -> R.drawable.starkerwind
            "tornado" in description.lowercase(Locale.ROOT) -> R.drawable.tornado
            "storm" in description.lowercase(Locale.ROOT) -> R.drawable.storm
            "snow" in description.lowercase(Locale.ROOT) -> R.drawable.schnee
            "fog" in description.lowercase(Locale.ROOT) -> R.drawable.nebel
            else -> R.drawable.teilweisewolken}}}