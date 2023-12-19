package de.hdmstuttgart.wetter

import android.app.Application
import de.hdmstuttgart.wetter.API.WeatherApi
import de.hdmstuttgart.wetter.Town.TownDatabase
import de.hdmstuttgart.wetter.Town.TownRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TownTrackerApplication : Application() {
    private val database by lazy { TownDatabase.getDatabase(this) }
    val repository by lazy { TownRepository(database.townDao()) }

    //todo: wetter api.
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Wetter api key: cc5ef9e3576dc1e8bc30087dae5ee9ca
    //The endpoint: https://api.openweathermap.org.
    //London: https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=cc5ef9e3576dc1e8bc30087dae5ee9ca

    val weatherApi: WeatherApi by lazy { retrofit.create(WeatherApi::class.java) }
}