package de.hdmstuttgart.wetter.datenmodellWeatherAPICall

import com.google.gson.annotations.SerializedName

/** Thats the data structure that the JSON has, that we get from the api openweathermap.org.
 * To get the Data Structure we used the following api-call:
 * https://api.openweathermap.org/data/2.5/weather?q=Manchester&appid=apikey/.
 * this structure we do transfer to kotlin classes right in this package,
 * with the tranformation tool https://json2kt.com/.
 **/
data class WeatherDataToday (

  @SerializedName("coord"      ) var coord      : Coord?             = Coord(),
  @SerializedName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf(),
  @SerializedName("base"       ) var base       : String?            = null,
  @SerializedName("main"       ) var main       : Main?              = Main(),
  @SerializedName("visibility" ) var visibility : Int?               = null,
  @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
  @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
  @SerializedName("dt"         ) var dt         : Int?               = null,
  @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
  @SerializedName("timezone"   ) var timezone   : Int?               = null,
  @SerializedName("id"         ) var id         : Int?               = null,
  @SerializedName("name"       ) var name       : String?            = null,
  @SerializedName("cod"        ) var cod        : Int?               = null)