package de.hdmstuttgart.wetter.datenmodellneuForecast

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/forecast?q=Saigon&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package.
 **/

data class Main (
  @SerializedName("temp"       ) var temp      : Double? = null,
  @SerializedName("feels_like" ) var feelsLike : Double? = null,
  @SerializedName("temp_min"   ) var tempMin   : Double? = null,
  @SerializedName("temp_max"   ) var tempMax   : Double? = null,
  @SerializedName("pressure"   ) var pressure  : Int?    = null,
  @SerializedName("sea_level"  ) var seaLevel  : Int?    = null,
  @SerializedName("grnd_level" ) var grndLevel : Int?    = null,
  @SerializedName("humidity"   ) var humidity  : Int?    = null,
  @SerializedName("temp_kf"    ) var tempKf    : Double? = null)