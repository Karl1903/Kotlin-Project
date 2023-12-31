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

        //Retrofit Setup to do API-Calls to the Weather-API openweathermap.org..
        private val retrofit by lazy {

            // The converter factory is responsible for converting
            // the raw response body into the expected return type.
               Retrofit.Builder()
                    .baseUrl(Configuration.BASE_LINK)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    }
    val weatherApi: WeatherApi by lazy { retrofit.create(WeatherApi::class.java) }
}