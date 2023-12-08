package de.hdmstuttgart.wetter.Town

import androidx.annotation.WorkerThread

class TownRepository(private val townDao: TownDao) {

    fun getTowns(): List<Town>{
        return townDao.getTowns()
    }

    @WorkerThread
    suspend fun insert(town: Town){
        townDao.insert(town)
    }

    @WorkerThread
    suspend fun delete(town: Town){
        townDao.delete(town)
    }
}