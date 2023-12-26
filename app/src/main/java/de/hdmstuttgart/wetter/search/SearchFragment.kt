package de.hdmstuttgart.wetter.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import de.hdmstuttgart.wetter.R

/** The Search Fragment empowers the user to type in the town he wants to get the weather data for.
 * Then, the user can click on the button with the text "Search Town".
 *  */

class SearchFragment : Fragment(R.layout.fragment_search) {

    //private val data = ArrayList<Town>()

    //private val adapter = TownAdapter(data)

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
        //val searchTitleButton = view.findViewById<Button>(R.id.searchWeatherTownButton)
        //searchTitleButton.setOnClickListener{
            //Get the text. the user writes the text for the name of the town.
            //val searchTownEditText = view.findViewById<EditText>(R.id.searchWeatherTownEditText)
            //val searchTownText = searchTownEditText.text.toString()

            //Search towns that fit to the text the user writes. So if the user just writes
            //few letters like "Li" the list will show many towns like "Lissabon", "Lichtenrade".
         //   searchTowns(searchTownText)
        //}
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

    //the towns are searched with the text provided from the user.
    //Then, the found town names are saved into the data-Array.

    //Todo: prüfen, was für daten die wetter api sendet. wert von town.
//    private fun searchTowns(townName: String) {

  //      activity?.let { it ->
  //          val townTrackerApplication = it.application as TownTrackerApplication

     //       lifecycleScope.launch(Dispatchers.IO) {
     //           val payload = townTrackerApplication.weatherApi.getWeatherResults(townName, "cc5ef9e3576dc1e8bc30087dae5ee9ca")
     //           Log.d("Data from api call:", "data from api is $payload")
     //           data.clear()

     //           val towns = payload.search.map { return@map it.toDomain()}
     //           data.addAll(towns)
     //           Log.d("Data towns:", "data towns: $data")

     //           withContext(Dispatchers.Main){
     //               adapter.notifyDataSetChanged()
     //           }
     //       }
     //   }
   // }
}