package de.hdmstuttgart.wetter.Town

import com.google.gson.annotations.SerializedName

//todo: change data structure to: Towns
/**The data structure to provide the data for the saved towns in the Main Activity list.
 * The name of the town, the description of the trend the weather is takin ("rainy", "sunny")
 * and the temperature trend in Celcius degrees.
 *
 * */
data class TownDTO (
    @SerializedName("Town-ID") val townID: String,
    @SerializedName("Name") val name: String,
    @SerializedName("Trend-Description") val description: String,
    @SerializedName("Trend-Degrees") val degrees: String,
){
    fun toDomain(): Town{
        return Town(townID = townID, name = name, description = description, degrees = degrees)
    }
}