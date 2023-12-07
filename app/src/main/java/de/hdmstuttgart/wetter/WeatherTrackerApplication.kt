package de.hdmstuttgart.movietracker

import android.app.Application
import de.hdmstuttgart.movietracker.API.WeatherApi
import de.hdmstuttgart.movietracker.Movie.WeatherDatabase
import de.hdmstuttgart.movietracker.Weather.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherTrackerApplication : Application() {
    private val database by lazy { WeatherDatabase.getDatabase(this) }
    val repository by lazy { WeatherRepository(database.weatherDao()) }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApi: WeatherApi by lazy { retrofit.create(WeatherApi::class.java) }
}