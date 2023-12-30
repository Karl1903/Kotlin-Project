package de.hdmstuttgart.wetter.Town

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Town::class], version = 2, exportSchema = false)
abstract class TownDatabase : RoomDatabase() {

    abstract fun townDao(): TownDao

    companion object{
        @Volatile
        private var INSTANCE: TownDatabase? = null

        fun getDatabase(context: Context): TownDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TownDatabase::class.java,
                    "town-database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}