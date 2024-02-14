package de.hdmstuttgart.wetter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdmstuttgart.wetter.Town.Town

interface WeatherDataClickListener {
    fun townListener(position: Int)
}

interface TrashClickListener {
    fun trashListener(position: Int)
}

    class TownAdapter(private val list: List<Town>,
                      private val weatherDataClickListener: WeatherDataClickListener,
                      private val trashClickListener: TrashClickListener
    ): RecyclerView.Adapter<TownAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.town_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val townModel = list[position]
        holder.nameView.text = townModel.name
        //holder.descriptionView.text = townModel.description
        //holder.temperatureView.text = townModel.temperature.toString()

        //Glide.with(holder.itemView.context).load(townModel.poster).into(holder.posterImageView)

        holder.itemView.setOnClickListener{
            weatherDataClickListener.townListener(position)
        }

        holder.trashIcon.setOnClickListener {
            // put the trash icon click event.
            trashClickListener.trashListener(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val trashIcon: ImageView = itemView.findViewById(R.id.trashIcon)
        val nameView: TextView = itemView.findViewById(R.id.name)
        //val descriptionView: TextView = itemView.findViewById(R.id.description)
        //val temperatureView: TextView = itemView.findViewById(R.id.temperature)
        //todo: What we could do here is an image that depends on the description
        //todo: if the weather description is "sunny" then we take the sun pic.
        //todo: description = rain. then rain.
        //todo: we got that in the Weather Description now.
        //todo:but it can be great in the Main Fragment List too.
        //val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
    }
}