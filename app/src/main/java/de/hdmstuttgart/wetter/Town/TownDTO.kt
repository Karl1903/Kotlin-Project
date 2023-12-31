package de.hdmstuttgart.wetter.Town

import android.health.connect.datatypes.units.Temperature
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * The class represents the data structure for the data
 * for the weather of a single town that is shown in the
 * weather fragment and in
 * the main activity's (recent searches, local storage) list.
 *  The name of the town, the description of the trend the weather is takin ("rainy", "sunny")
 *  and the temperature trend in Celcius degrees.
 */

@Entity
data class TownDTO (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    //temperature
    @SerializedName("temperature") val temperature: String,
    @SerializedName("windtempo") val windtempo: String,
    // we can add an icon from the folder res.drawable.
    //@SerializedName("icon") val icon: String? = null,
)
{
    fun toDomain(): Town{
        return Town(
            id = id,
            name = name,
            description = description,
            temperature = temperature,
            windtempo = windtempo)
    }
}