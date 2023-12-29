package de.hdmstuttgart.wetter.Town

import androidx.annotation.WorkerThread

class TownRepository(private val townDao: TownDao) {

    fun getTowns(): List<TownDTO>{
        return townDao.getTowns()
    }

    @WorkerThread
    suspend fun insert(townDTO: TownDTO){
        townDao.insert(townDTO)
    }

    @WorkerThread
    suspend fun delete(townDTO: TownDTO){
        townDao.delete(townDTO)
    }
}