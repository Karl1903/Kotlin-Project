package de.hdmstuttgart.wetter.Weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getAllWeathers():List<Weather>

    @Insert
    suspend fun insert(weather: Weather)

    @Delete
    suspend fun delete(weather: Weather)
}