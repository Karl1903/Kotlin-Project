package de.hdmstuttgart.wetter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Weather.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment(R.layout.fragment_main), WeatherCLickListener {

    private val data = ArrayList<Weather>()

    private val adapter = WeatherAdapter(data, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        recyclerView.adapter = adapter
    }

    private fun update() {
        activity?.let {
            val fragmentActivity = it
            val weatherTrackerApplication = fragmentActivity.application as WeatherTrackerApplication

            lifecycleScope.launch(Dispatchers.IO) {
                data.clear()
                data.addAll(weatherTrackerApplication.repository.getAllWeathers())

                withContext(Dispatchers.Main){
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        update()
    }

    override fun onWeatherClickListener(position: Int) {
        val weather = data[position]

        activity?.let {
            val fragmentActivity = it
            val weatherTrackerApplication = fragmentActivity.application as WeatherTrackerApplication

            lifecycleScope.launch (Dispatchers.IO ){
                weatherTrackerApplication.repository.delete(weather)

                withContext(Dispatchers.Main){
                    update()
                }
            }
        }
    }
}





