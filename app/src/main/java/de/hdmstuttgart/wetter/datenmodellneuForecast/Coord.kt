package de.hdmstuttgart.wetter.datenmodellneuForecast

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/forecast?q=Saigon&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package.
 **/

data class Coord (
  @SerializedName("lat" ) var lat : Double? = null,
  @SerializedName("lon" ) var lon : Double? = null)