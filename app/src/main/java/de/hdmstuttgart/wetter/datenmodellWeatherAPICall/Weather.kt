package de.hdmstuttgart.wetter.datenmodellWeatherAPICall

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/weather?q=Manchester&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package,
 * with the tranformation tool https://json2kt.com/.
 **/
data class Weather (

  @SerializedName("id"          ) var id          : Int?    = null,
  @SerializedName("main"        ) var main        : String? = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("icon"        ) var icon        : String? = null)