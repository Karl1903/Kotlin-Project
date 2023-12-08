package de.hdmstuttgart.wetter.Town

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TownDao {

    @Query("SELECT * FROM town")
    fun getTowns():List<Town>

    @Insert
    suspend fun insert(town: Town)

    @Delete
    suspend fun delete(town: Town)
}