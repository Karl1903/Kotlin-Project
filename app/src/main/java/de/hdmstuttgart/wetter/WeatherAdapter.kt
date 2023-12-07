package de.hdmstuttgart.movietracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdmstuttgart.movietracker.Weather.Weather

interface WeatherCLickListener {

    fun onWeatherClickListener(position: Int)
}

    class WeatherAdapter(private val list: List<Weather>,
                       private val weatherCLickListener: WeatherCLickListener): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherModel = list[position]
        holder.titleView.text = weatherModel.title
        holder.yearView.text = weatherModel.year

        Glide.with(holder.itemView.context).load(weatherModel.poster).into(holder.posterImageView)

        holder.itemView.setOnClickListener{
            weatherCLickListener.onWeatherClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById(R.id.title)
        val yearView: TextView = itemView.findViewById(R.id.year)
        val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
    }
}