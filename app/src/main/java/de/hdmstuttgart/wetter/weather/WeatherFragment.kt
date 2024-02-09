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
import de.hdmstuttgart.wetter.Configuration
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.Town
import de.hdmstuttgart.wetter.TownTrackerApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


//The Weather Fragment that shows the weather data for one single town
// for the date right now.
// 1.the activity with the fragment is started when the user clicks
// the row with the town in the list in the main fragment.

// 2.the activity with the fragment is started when the user clicks
// the button to search the data for the town in the search Fragment.
class WeatherFragment : Fragment() {

    private lateinit var townNameTextView: TextView
    private lateinit var descriptionTextViewNow: TextView
    private lateinit var temperatureTextViewNow: TextView
    private lateinit var windtempoTextViewNow: TextView
    private lateinit var dateTextViewNow: TextView

    private lateinit var iconImageViewNow: ImageView

    private lateinit var descriptionTextViewNextDay: TextView
    private lateinit var temperatureTextViewNextDay: TextView
    private lateinit var windtempoTextViewNextDay: TextView
    private lateinit var dateTextViewNextDay: TextView

    private lateinit var iconImageViewNextDay: ImageView

    private lateinit var descriptionTextViewDayAfterNextDay: TextView
    private lateinit var temperatureTextViewDayAfterNextDay: TextView
    private lateinit var windtempoTextViewDayAfterNextDay: TextView
    private lateinit var dateTextViewDayAfterNextDay: TextView

    private lateinit var iconImageViewDayAfterNextDay: ImageView

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
        descriptionTextViewNow = view.findViewById(R.id.descriptionTextViewNow)
        temperatureTextViewNow = view.findViewById(R.id.temperatureTextViewNow)
        windtempoTextViewNow = view.findViewById(R.id.windtempoTextViewNow)
        iconImageViewNow = view.findViewById(R.id.iconImageViewNow)
        dateTextViewNow = view.findViewById(R.id.dateTextViewNow)

        descriptionTextViewNextDay = view.findViewById(R.id.descriptionTextViewNextDay)
        temperatureTextViewNextDay = view.findViewById(R.id.temperatureTextViewNextDay)
        windtempoTextViewNextDay = view.findViewById(R.id.windtempoTextViewNextDay)
        iconImageViewNextDay = view.findViewById(R.id.iconImageViewNextDay)
        dateTextViewNextDay = view.findViewById(R.id.dateTextViewNextDay)

        descriptionTextViewDayAfterNextDay = view.findViewById(R.id.descriptionTextViewDayAfterNextDay)
        temperatureTextViewDayAfterNextDay = view.findViewById(R.id.temperatureTextViewDayAfterNextDay)
        windtempoTextViewDayAfterNextDay = view.findViewById(R.id.windtempoTextViewDayAfterNextDay)
        iconImageViewDayAfterNextDay = view.findViewById(R.id.iconImageViewDayAfterNextDay)
        dateTextViewDayAfterNextDay = view.findViewById(R.id.dateTextViewDayAfterNextDay)

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

        //val id = arguments?.getInt("id")
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
            id= "id",
            name = "townName",
            descriptionNow = "descriptionNow",
            temperatureNow = "temperatureNow",
            windtempoNow = "windtempoNow",
            //data for the next day..
            descriptionNextDay = "descriptionNextDay",
            temperatureNextDay = "temperatureNextDay",
            windtempoNextDay = "windtempoNextDay",
            //data for the day after the next day..
            descriptionDayAfterNextDay = "descriptionDayAfterNextDay",
            temperatureDayAfterNextDay = "temperatureDayAfterNextDay",
            windtempoDayAfterNextDay = "windtempoDayAfterNextDay")
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
        val dateNow = addTimeToTheDate(0)
        townNameTextView.text = "Location: ${town.name}"
        dateTextViewNow.text = "Date: $dateNow"
        descriptionTextViewNow.text = "Weather description: ${town.descriptionNow}"
        temperatureTextViewNow.text = "Temperature: ${town.temperatureNow}"
        windtempoTextViewNow.text = "Wind tempo: ${town.windtempoNow}"
        // get the picture with Glide based on the description.
        val iconResource = getIconResource(town.descriptionNow)
        Glide.with(requireContext())
            .load(iconResource)
            .into(iconImageViewNow)

        //Next day data:
        val dateNextDay = addTimeToTheDate(24)
        dateTextViewNextDay.text = "Date: $dateNextDay"
        descriptionTextViewNextDay.text = "Weather description: ${town.descriptionNextDay}"
        temperatureTextViewNextDay.text = "Temperature: ${town.temperatureNextDay}"
        windtempoTextViewNextDay.text = "Wind tempo: ${town.windtempoNextDay}"
        // get the picture with Glide based on the description.
        val iconResourceNextDay = getIconResource(town.descriptionNextDay)
        Glide.with(requireContext())
            .load(iconResourceNextDay)
            .into(iconImageViewNextDay)

        //Day after next day data:
        val dateDayAfterNextDay = addTimeToTheDate(48)
        dateTextViewDayAfterNextDay.text = "Date: $dateDayAfterNextDay"
        descriptionTextViewDayAfterNextDay.text = "Weather description: ${town.descriptionDayAfterNextDay}"
        temperatureTextViewDayAfterNextDay.text = "Temperature: ${town.temperatureDayAfterNextDay}"
        windtempoTextViewDayAfterNextDay.text = "Wind tempo: ${town.windtempoDayAfterNextDay}"
        // get the picture with Glide based on the description.
        val iconResourceDayAfterNextDay = getIconResource(town.descriptionDayAfterNextDay)
        Glide.with(requireContext())
            .load(iconResourceDayAfterNextDay)
            .into(iconImageViewDayAfterNextDay)

    }

    private fun addTimeToTheDate(timeToAdd: Int): String {
        val calendar: Calendar = Calendar.getInstance()
        val date = calendar.time
        calendar.setTime(date)
        calendar.add(Calendar.HOUR_OF_DAY, timeToAdd)
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return formatter.format(calendar.time)
    }
    private fun getIconResource(description: String): Int {
        // There are different PNG files for each weather condition.
        return when {
            "rain" in description.lowercase(Locale.ROOT) -> R.drawable.rainy
            "clear" in description.lowercase(Locale.ROOT) -> R.drawable.sun
            "cloud" in description.lowercase(Locale.ROOT) -> R.drawable.clouds
            "sunny" in description.lowercase(Locale.ROOT) -> R.drawable.sun
            "scattered" in description.lowercase(Locale.ROOT) -> R.drawable.partlycloudy
            "wind" in description.lowercase(Locale.ROOT) -> R.drawable.wind
            "tornado" in description.lowercase(Locale.ROOT) -> R.drawable.hurricane
            "storm" in description.lowercase(Locale.ROOT) -> R.drawable.thunder
            "snow" in description.lowercase(Locale.ROOT) -> R.drawable.snow
            "fog" in description.lowercase(Locale.ROOT) -> R.drawable.fog
            else -> R.drawable.partlycloudy}}}
