package de.hdmstuttgart.wetter.API

import com.google.gson.annotations.SerializedName
import de.hdmstuttgart.wetter.Weather.WeatherDTO

class SearchResponse(
    @SerializedName("Search") var search: List<WeatherDTO> )