package de.hdmstuttgart.wetter.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.hdmstuttgart.wetter.R
import de.hdmstuttgart.wetter.Town.Town



//todo: create a Fragment that shows the weather data for one single town.
//the activity and fragment is started when the user clicks on a town in the list
//of the main activity.

//also the activity and fragment are started when the user clicks on a town in the search list.

class WeatherFragment : Fragment() {

    private lateinit var townNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var temperatureTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        townNameTextView = view.findViewById(R.id.townNameTextView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        temperatureTextView = view.findViewById(R.id.temperatureTextView)


        val town = arguments?.getString("townID")
        val name = arguments?.getString("name")
        val description = arguments?.getString("description")
        val degrees = arguments?.getString("degrees")

        val townData = Town(townID = town.orEmpty(), name = name.orEmpty(), description = description.orEmpty(), degrees = degrees.orEmpty())

        updateUI(townData)

        return view
    }

    private fun updateUI(town: Town) {
        townNameTextView.text = town.name
        descriptionTextView.text = town.description
        temperatureTextView.text = "Temperature: ${town.degrees} °C"
    }
}

