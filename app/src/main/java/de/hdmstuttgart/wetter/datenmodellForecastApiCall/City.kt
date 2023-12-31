package de.hdmstuttgart.wetter.datenmodellForecastApiCall

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/forecast?q=London&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package.
 **/
data class City (
  @SerializedName("id") var id: Int? = null,
  @SerializedName("name") var name: String? = null,
  @SerializedName("coord") var coord: Coord? = Coord(),
  @SerializedName("country") var country: String? = null,
  @SerializedName("population") var population: Int? = null,
  @SerializedName("timezone") var timezone: Int? = null,
  @SerializedName("sunrise") var sunrise: Int? = null,
  @SerializedName("sunset") var sunset: Int? = null)