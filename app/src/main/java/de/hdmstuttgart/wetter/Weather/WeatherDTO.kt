package de.hdmstuttgart.wetter.Weather

import com.google.gson.annotations.SerializedName

data class WeatherDTO (
    @SerializedName("imdbID") val uid: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val poster: String,
){
    fun toDomain(): Weather{
        return Weather(uid = uid, title = title, year = year, poster = poster)
    }
}