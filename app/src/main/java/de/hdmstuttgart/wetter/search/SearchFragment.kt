package de.hdmstuttgart.wetter.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import de.hdmstuttgart.wetter.MainActivity
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.Town
import de.hdmstuttgart.wetter.TownTrackerApplication
import de.hdmstuttgart.wetter.weather.WeatherActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/** The Search Fragment empowers the user to type in the town he wants to get the weather data for.
 * Then, the user can click on the button with the text "Search Town".
 *  */
class SearchFragment : Fragment(R.layout.fragment_search) {

    //private val data = ArrayList<Town>()
    private var message = ""
    //private val adapter = TownAdapter(ArrayList<Town>())

    //Town(-weather) search with town-name and button click.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set the search Button so the User can Search for a Town.
        val searchTitleButton = view.findViewById<Button>(R.id.searchWeatherTownButton)
        searchTitleButton.setOnClickListener{
            //Get the text.
            //the user writes the text for the name of the town.
            val searchTownEditText = view.findViewById<EditText>(R.id.searchWeatherTownEditText)
            val searchTownText = searchTownEditText.text.toString()


            //Search the town that fits to the text the user writes.
            //Then, the weather data for this town is saved to the database.
            //Then, the user is navigated to the weather activity.
            //there, the data is retrieved from the database and presented.
                searchTown(searchTownText)


        }
    }

    //Todo: Delete this method when it is safe that we dont neet it.
    //Todo: Right now the flow of the application doesn't need it
    //Todo: cause it doesn't get a list of towns but just one town with the weather data for it.
    //Town gets clicked, saved into the repository that saves the clicked towns.
    //The purpose of this is that the user has the list with the recent searches he did in the
    //Search Activity. So he can access the weather data for his/her favorite towns
    // fast without the need to write the town name again.
    //override fun onTownClickListener(position: Int) {
    //    val town = data.get(position)

    //    activity?.let {
    //        val fragmentActivity = it
    //        val townTrackerApplication = fragmentActivity.application as TownTrackerApplication

     //       lifecycleScope.launch (Dispatchers.IO ){
      //          townTrackerApplication.repository.insert(town)

       //         withContext(Dispatchers.Main){
        //            fragmentActivity.finish()
        //        }
        //    }
       // }
   // }


    /**
     * the get call is done to the openweathermap-API with the text from the user.
     * In case the text matches a town that the openweathermap-API has data for
     * the weather data for the town gets fetched.
     * The data is converted from JSON to Kotlin Objects with the retrofit converter.
     *  Then we can access the data and save the town to the TownDatabase.
     *
     * */
    private fun searchTown(townName: String) {

       activity?.let { it ->
           val townTrackerApplication = it.application as TownTrackerApplication


           lifecycleScope.launch(Dispatchers.IO) {
               try {
               Log.d("townName", "townName: $townName")
               val dataNow = townTrackerApplication.weatherApi.getWeatherData(
                   townName,
                   "cc5ef9e3576dc1e8bc30087dae5ee9ca"
               )
               val dataNextWeek = townTrackerApplication.weatherApi.getWeatherResults(
                   townName,
                   "cc5ef9e3576dc1e8bc30087dae5ee9ca"
               )

               //The Weather Object (Next Day) is in a List so we need to access this List.
               val dataNextDay = dataNextWeek.list[3]
               //the data for next next day.
               val dataNextNextDay = dataNextWeek.list[10]


               //description next next day
               val wetterTraditionell = dataNextDay.weather[0].description.toString()
               //val wetter1 = dataNextDay.weather[1].description.toString()
               Log.d("Wetter traditionell", "Wetter traditionell: $wetterTraditionell")
               //Log.d("Wetter 1", "Wetter 1: $wetter1")

               // the description is in the main value but additional in the description value.
               val descriptionNow =
                   dataNow.weather[0].main.toString() + ", " + dataNow.weather[0].description.toString() + "."
               val descriptionNextDay = dataNextDay.weather[0].description.toString() + "."
               val descriptionDayAfterNextDay =
                   dataNextNextDay.weather[0].description.toString() + "."

               //The Temperature is given in Kelvin, se we need to substract
               // -273,15 to get the value in Celsius.
               //futhermore we need to cut the decimals (Nachkommastellen)
               // to max. two decimals.
               val temperatureNow = formatTemperature(dataNow.main?.temp)
               val temperatureNextDay = formatTemperature(dataNextDay.main?.temp)
               val temperatureDayAfterNextDay = formatTemperature(dataNextNextDay.main?.temp)

               //wind tempo format.
               var windtempoNow = formatWindtempo(dataNow.wind?.speed.toString())
               var windtempoNextDay = formatWindtempo(dataNextDay.wind?.speed.toString())
               var windtempoDayAfterNextDay =
                   formatWindtempo(dataNextNextDay.wind?.speed.toString())


               //to get a List. we dont need that 93%.
               //val dataResponse = payload.search.map { return@map it.toDomain()}

               //val townDTO = TownDTO(
               //          keyID = 1,
               //          id = 1,
               //          name = "London",
               //          description = "sunny",
               //          temperature = "13C")

               // Take the data from the API response.
               val townNew = Town(
                   id = dataNow.id.toString(),
                   name = townName,
                   descriptionNow = descriptionNow,
                   temperatureNow = temperatureNow,
                   windtempoNow = windtempoNow,
                   //data for the next day..
                   descriptionNextDay = descriptionNextDay,
                   temperatureNextDay = temperatureNextDay,
                   windtempoNextDay = windtempoNextDay,
                   //data for the day after the next day..
                   descriptionDayAfterNextDay = descriptionDayAfterNextDay,
                   temperatureDayAfterNextDay = temperatureDayAfterNextDay,
                   windtempoDayAfterNextDay = windtempoDayAfterNextDay
               )

               //Check if the Town already is in the database.
               //If yes, delete the town and add it again cause the data
               //may have changed for the weather in the meantime.
               //if not, just put the retrieved town-data into the Room database.
               val towns = townTrackerApplication.repository.getTowns()
               for (town in towns) {
                   if (town.name == townName) {
                       townTrackerApplication.repository.delete(town)
                   }
               }
               townTrackerApplication.repository.insert(townNew)

               //Navigates to the weather activity.
               activity?.let {
                   val intent = Intent(it, WeatherActivity::class.java)
                   // the key townName with
                   // the data is passed to the WeatherActivity.
                   intent.putExtra("townName", townName)
                   it.startActivity(intent)
               }
               }  catch (e: Exception) {
                   // Handle API call failure or parsing error
                   Log.d("api call error message:", "api call error message: ${e.message}")
                   it.runOnUiThread {
                       Toast.makeText(
                           requireContext(),
                           getString(R.string.no_valid_town_name_message),
                           Toast.LENGTH_SHORT
                       ).show()
                   }
               }
           }


           }

        }


    private fun formatWindtempo(windtempoString: String): String {
        var windtempoNow = windtempoString + getString(R.string.wind_metric_plural)
        // 1 meter.
        if (windtempoString == "1" || windtempoString == "1.0" || windtempoString == "1.00"){
            windtempoNow = windtempoString + getString(R.string.wind_metric_singular)
        }
        return windtempoNow

    }

    //The Temperature is given in Kelvin, se we need to substract
    // -273,15 to get the value in Celsius.
    //futhermore we need to cut the decimals (Nachkommastellen)
    // to max. two decimals.
    private fun formatTemperature(temperatureKelvin: Double?): String {
        val temperatureNotCut = temperatureKelvin?.minus(273.15)
        val temperatureString = ((temperatureNotCut?.times(100.0) ?: 0.0) / 100.0).roundToInt().toString()
        var temperature = temperatureString + getString(R.string.temperature_metric_plural)
        // 1 degree.
        if (temperatureString == "1" || temperatureString == "-1"){
            temperature = temperatureString + getString(R.string.temperature_metric_singular)
        }
        return temperature
    }
}