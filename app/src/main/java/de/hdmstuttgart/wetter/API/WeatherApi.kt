package de.hdmstuttgart.wetter.API

import de.hdmstuttgart.wetter.Configuration
import de.hdmstuttgart.wetter.Town.TownDTO
import de.hdmstuttgart.wetter.model.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The GET-Reqeust gets defined,
 * the user types in a town and gets the
 * weather data for it.
 * the programm appends the api-key.
 *
 **/
public interface WeatherApi {

    @GET("weather")
    fun getCurrentWeatherData(
    @Query("q") location: String,
    @Query("appid") apiKey: String = Configuration.API_KEY):
            TownDTO;

    @GET("forecast?")
    suspend fun getWeatherResults(
        @Query("q") townName: String,
        @Query("appid") apikey: String = Configuration.API_KEY
    ): City
}