package de.hdmstuttgart.wetter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdmstuttgart.wetter.Town.Town
import de.hdmstuttgart.wetter.weather.WeatherActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment(R.layout.fragment_main),
    WeatherDataClickListener, TrashClickListener {

    private val data = ArrayList<Town>()

    private val adapter = TownAdapter(data, this, this)

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
            val townTrackerApplication = fragmentActivity.application as TownTrackerApplication

            lifecycleScope.launch(Dispatchers.IO) {
                data.clear()
                data.addAll(townTrackerApplication.repository.getTowns())

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

    //Listens to the click on the town name.
    //if the town name gets clicked
    //the application navigated to
    //the weather fragment.
    override fun townListener(position: Int) {
        val townName = data[position].name
        //Navigates to the weather activity.
        activity?.let {
            val intent = Intent(it, WeatherActivity::class.java)
            // the key townName is passed to the WeatherActivity
            intent.putExtra("townName", townName)
            it.startActivity(intent)
        }
    }

    //Listens to the Click on the Trash Icon.
    //If the user clicks it the line is deleted.
    override fun trashListener(position: Int){
        val town = data[position]

        activity?.let {
            val fragmentActivity = it
            val townTrackerApplication = fragmentActivity.application as TownTrackerApplication

            lifecycleScope.launch (Dispatchers.IO ){
                townTrackerApplication.repository.delete(town)

                withContext(Dispatchers.Main){
                    update()
                }
            }
        }
    }
}





