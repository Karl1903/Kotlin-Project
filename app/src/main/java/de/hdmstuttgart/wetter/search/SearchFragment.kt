package de.hdmstuttgart.wetter.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.Town
import de.hdmstuttgart.wetter.Town.TownDTO
import de.hdmstuttgart.wetter.TownTrackerApplication
import de.hdmstuttgart.wetter.weather.WeatherActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** The Search Fragment empowers the user to type in the town he wants to get the weather data for.
 * Then, the user can click on the button with the text "Search Town".
 *  */

class SearchFragment : Fragment(R.layout.fragment_search) {

    //private val data = ArrayList<Town>()

    //private val adapter = TownAdapter(ArrayList<Town>())

    //Town(-weather) search with town-name and button click.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get recycler view and set it to a val.
        //val recyclerView = view.findViewById<RecyclerView>(R.id.searchRecyclerView)

        //recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        //Connect recyclerView to the Adapter to fill it with the Data.
        //recyclerView.adapter = adapter

        //Set the search Button so the User can Search for a Town.
        val searchTitleButton = view.findViewById<Button>(R.id.searchWeatherTownButton)
        searchTitleButton.setOnClickListener{
            //Get the text. the user writes the text for the name of the town.
            val searchTownEditText = view.findViewById<EditText>(R.id.searchWeatherTownEditText)
            val searchTownText = searchTownEditText.text.toString()

            //Search the town that fits to the text the user writes.
            //Then, the weather data for this town is saved to the database.
            //Then, the user is navigated to the weather activity.
            //there, the data is retrieved from the database and presented.
            searchTown(searchTownText)

            //Navigates to the weather activity.
            activity?.let {
                val intent = Intent(it, WeatherActivity::class.java)
                it.startActivity(intent)
            }
        }
    }

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

    // the get call is done to the openweathermap-API with the text from the user.
    // In case the text matches a town that the openweathermap-API has data for
    // the weather data for the town gets fetched.
    // The data is converted from JSON to Kotlin Objects with the retrofit converter.
    // Then we can access the data and save the town to the TownDatabase.

    //Todo: prüfen, was für daten die wetter api sendet. wert von town.
    private fun searchTown(townName: String) {

       activity?.let { it ->
            val townTrackerApplication = it.application as TownTrackerApplication

           try {
            lifecycleScope.launch(Dispatchers.IO) {
                Log.d("townName", "townName: $townName")
                val response = townTrackerApplication.weatherApi.getWeatherData(townName, "cc5ef9e3576dc1e8bc30087dae5ee9ca")
                val payload = response.search.toDomain()

                //val town = payload.search.map { return@map it.toDomain()}
                Log.d("Data from api call:", "data from api is $payload")

                //to get a List. we dont need that 93%.
                //val dataResponse = payload.search.map { return@map it.toDomain()}

               //Log.d("Data wetter:", "data wetter: $dataResponse")

                //val townDTO = TownDTO(
                  //          keyID = 1,
                  //          id = 1,
                  //          name = "London",
                  //          description = "sunny",
                  //          temperature = "13C")

                // Extract relevant information from the API response
                val town = Town(
                    id = payload.id,
                    name = payload.name,
                    description = payload.description,  // Extracting the first weather description
                    temp = payload.temp  // Extracting temperature
                )
                // Insert the retrieved data into the Room database
                townTrackerApplication.repository.insert(town)

                //withContext(Dispatchers.Main){
                    //adapter.notifyDataSetChanged()
               // }
            }

           } catch (e: Exception) {
               // Handle API call failure or parsing error
               Log.d("api call error message:", "api call error message: ${e.message}")
           }
        }
    }
}