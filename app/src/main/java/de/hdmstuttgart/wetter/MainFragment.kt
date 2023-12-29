package de.hdmstuttgart.wetter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdmstuttgart.wetter.Town.TownDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment(R.layout.fragment_main), TownClickListener {

    private val data = ArrayList<TownDTO>()

    private val adapter = TownAdapter(data, this)

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



    override fun onTownClickListener(position: Int) {
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





