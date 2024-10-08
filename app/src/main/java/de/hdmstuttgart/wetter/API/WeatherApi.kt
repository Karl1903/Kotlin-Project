package de.hdmstuttgart.wetter.API

import de.hdmstuttgart.wetter.datenmodellWeatherAPICall.WeatherDataToday
import de.hdmstuttgart.wetter.datenmodellneuForecast.WeatherDataNextWeek
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
    suspend fun getWeatherData(
    @Query("q") townName: String,
    @Query("appid") apiKey: String = Configuration.API_KEY):
            WeatherDataToday;

    @GET("forecast")
    suspend fun getWeatherResults(
        @Query("q") townName: String,
        @Query("appid") apikey: String = Configuration.API_KEY
    ): WeatherDataNextWeek;
}