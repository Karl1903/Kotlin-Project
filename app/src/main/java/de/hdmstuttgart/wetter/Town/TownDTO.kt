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
    //data for now..
    @SerializedName("description") val descriptionNow: String,
    //temperature
    @SerializedName("temperature") val temperatureNow: String,
    @SerializedName("windtempo") val windtempoNow: String,
    //data for the next day..
    @SerializedName("description") val descriptionNextDay: String,
    @SerializedName("temperature") val temperatureNextDay: String,
    @SerializedName("windtempo") val windtempoNextDay: String,
    //data for the day after the next day..
    @SerializedName("description") val descriptionDayAfterNextDay: String,
    @SerializedName("temperature") val temperatureDayAfterNextDay: String,
    @SerializedName("windtempo") val windtempoDayAfterNextDay: String,
    // we can add an icon from the folder res.drawable.
    //@SerializedName("icon") val icon: String? = null,
)
{
    fun toDomain(): Town{
        return Town(
            id = id,
            name = name,
            descriptionNow = descriptionNow,
            temperatureNow = temperatureNow,
            windtempoNow = windtempoNow,
            descriptionNextDay = descriptionNextDay,
            temperatureNextDay = temperatureNextDay,
            windtempoNextDay = windtempoNextDay,
            descriptionDayAfterNextDay = descriptionDayAfterNextDay,
            temperatureDayAfterNextDay = temperatureDayAfterNextDay,
            windtempoDayAfterNextDay = windtempoDayAfterNextDay)
    }
}