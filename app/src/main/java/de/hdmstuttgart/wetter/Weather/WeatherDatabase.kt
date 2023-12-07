package de.hdmstuttgart.movietracker.Movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.hdmstuttgart.movietracker.Weather.Weather
import de.hdmstuttgart.movietracker.Weather.WeatherDao


@Database(entities = [Weather::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object{
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather-database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}