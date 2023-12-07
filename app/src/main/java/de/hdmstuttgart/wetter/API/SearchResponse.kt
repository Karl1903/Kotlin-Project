package de.hdmstuttgart.movietracker.API

import com.google.gson.annotations.SerializedName
import de.hdmstuttgart.movietracker.Weather.WeatherDTO

class SearchResponse(
    @SerializedName("Search") var search: List<WeatherDTO>
)