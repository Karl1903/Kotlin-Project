package de.hdmstuttgart.wetter.datenmodellForecastApiCall

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/forecast?q=London&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package,
 * with the tranformation tool https://json2kt.com/.
 **/
data class Wind (
  @SerializedName("speed") var speed: Double? = null,
  @SerializedName("deg") var deg: Int? = null,
  @SerializedName("gust") var gust: Double? = null)