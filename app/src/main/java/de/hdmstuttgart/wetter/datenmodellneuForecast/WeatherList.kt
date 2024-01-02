package de.hdmstuttgart.wetter.datenmodellneuForecast

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/forecast?q=Saigon&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package.
 **/

data class WeatherList (
  @SerializedName("dt"         ) var dt         : Int?               = null,
  @SerializedName("main"       ) var main       : Main?              = Main(),
  @SerializedName("weather"    ) var weather    : ArrayList<WeatherData> = arrayListOf(),
  @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
  @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
  @SerializedName("visibility" ) var visibility : Int?               = null,
  @SerializedName("pop"        ) var pop        : Double?               = null,
  @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
  @SerializedName("dt_txt"     ) var dtTxt      : String?            = null)