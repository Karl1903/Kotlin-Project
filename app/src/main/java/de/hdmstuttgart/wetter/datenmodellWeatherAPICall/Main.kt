package com.example.example

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/weather?q=Manchester&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package,
 * with the tranformation tool https://json2kt.com/.
 **/
data class Main (
  @SerializedName("temp"       ) var temp      : Double? = null,
  @SerializedName("feels_like" ) var feelsLike : Double? = null,
  @SerializedName("temp_min"   ) var tempMin   : Double? = null,
  @SerializedName("temp_max"   ) var tempMax   : Double? = null,
  @SerializedName("pressure"   ) var pressure  : Int?    = null,
  @SerializedName("humidity"   ) var humidity  : Int?    = null)