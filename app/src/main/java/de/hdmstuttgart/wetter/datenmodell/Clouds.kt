package de.hdmstuttgart.wetter.datenmodell

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/forecast?q=London&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package.
 **/
data class Clouds (

  @SerializedName("all") var all: Int? = null

)