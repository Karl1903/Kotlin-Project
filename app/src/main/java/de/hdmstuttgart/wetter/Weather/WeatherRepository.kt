package de.hdmstuttgart.movietracker.Weather

import androidx.annotation.WorkerThread

class WeatherRepository(private val weatherDao: WeatherDao) {

    fun getAllWeathers(): List<Weather>{
        return weatherDao.getAllWeathers()
    }

    @WorkerThread
    suspend fun insert(weather: Weather){
        weatherDao.insert(weather)
    }

    @WorkerThread
    suspend fun delete(weather: Weather){
        weatherDao.delete(weather)
    }
}