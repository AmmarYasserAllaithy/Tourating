package com.ammaryasser.tourating.repository

import android.content.Context
import com.ammaryasser.tourating.data.Tourating
import com.ammaryasser.tourating.data.TouratingDao
import com.ammaryasser.tourating.data.TouratingDatabase
import kotlinx.coroutines.flow.Flow


class TouratingRepository(private val touratingDao: TouratingDao) {

    suspend fun insertOrUpdate(tourating: Tourating) = touratingDao.insertOrUpdate(tourating)

    suspend fun delete(tourating: Tourating) = touratingDao.delete(tourating)

    fun getAll(): Flow<List<Tourating>> = touratingDao.getAll()

    fun getById(id: Int): Flow<Tourating> = touratingDao.getById(id)

    fun getByLatLongBetween(
        fromLatitude: Int,
        toLatitude: Int,
        fromLongitude: Int,
        toLongitude: Int
    ): Flow<List<Tourating>> = touratingDao.getByLatLongBetween(
        fromLatitude,
        toLatitude,
        fromLongitude,
        toLongitude
    )


    companion object {
        private var repo: TouratingRepository? = null

        fun getInstance(context: Context): TouratingRepository {
            return repo ?: synchronized(this) {
                TouratingRepository(TouratingDatabase.getDatabase(context).touratingDao())
                    .also { repo = it }
            }
        }
    }

}