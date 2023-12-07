package de.hdmstuttgart.wetter.Search

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Weather.Weather
import de.hdmstuttgart.wetter.WeatherAdapter
import de.hdmstuttgart.wetter.WeatherCLickListener
import de.hdmstuttgart.wetter.WeatherTrackerApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchFragment : Fragment(R.layout.fragment_search), WeatherCLickListener {

    private val data = ArrayList<Weather>()

    private val adapter = WeatherAdapter(data, this)

    //Film search with title text and button click.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get recycler view and set it to a val.
        val recyclerView = view.findViewById<RecyclerView>(R.id.searchRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        //Connect recyclerView to the Adapter to fill it with the Data.
        recyclerView.adapter = adapter

        //Set the search Button so the User can Search for a Film.
        val searchTitleButton = view.findViewById<Button>(R.id.searchWeatherTitleButton)
        searchTitleButton.setOnClickListener{
            //Get the text from the User, he gives the Title.
            val searchTitleTextEditText = view.findViewById<EditText>(R.id.searchWeatherTitleEditText)
            val searchTitleText = searchTitleTextEditText.text.toString()

            searchMovies(searchTitleText)
        }
    }

    //Film gets clicked, saved into the repository.
    override fun onWeatherClickListener(position: Int) {
        val movie = data.get(position)

        activity?.let {
            val fragmentActivity = it
            val weatherTrackerApplication = fragmentActivity.application as WeatherTrackerApplication

            lifecycleScope.launch (Dispatchers.IO ){
                weatherTrackerApplication.repository.insert(movie)

                withContext(Dispatchers.Main){
                    fragmentActivity.finish()
                }
            }
        }
    }

    private fun searchMovies(searchTitleText: String) {

        activity?.let { it ->
            val weatherTrackerApplication = it.application as WeatherTrackerApplication

            lifecycleScope.launch(Dispatchers.IO) {
                val payload = weatherTrackerApplication.weatherApi.getSearchResult(searchTitleText, "7cb19ac6")
                data.clear()

                val movies = payload.search.map { return@map it.toDomain()}
                data.addAll(movies)

                withContext(Dispatchers.Main){
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}