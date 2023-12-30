package de.hdmstuttgart.wetter.Town

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Town Data Access Object to access the saved towns.
 * do tasks for the towns.
 * insert a new town.
 * get the towns.
 * delete.
 * */
@Dao
interface TownDao {

    @Query("SELECT * FROM town")
    fun getTowns():List<Town>

    @Insert
    suspend fun insert(town: Town)

    @Delete
    suspend fun delete(town: Town)
}