package com.ammaryasser.tourating.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TouratingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(tourating: Tourating)

    @Delete
    suspend fun delete(tourating: Tourating)

    @Query("SELECT * FROM $TOURATING_TABLE_NAME")
    fun getAll(): Flow<List<Tourating>>

    @Query("SELECT * FROM $TOURATING_TABLE_NAME WHERE id = :id")
    fun getById(id: Int): Flow<Tourating>

    @Query(
        "SELECT * FROM $TOURATING_TABLE_NAME" +
                " WHERE latitude BETWEEN :fromLatitude AND :toLatitude" +
                " AND longitude BETWEEN :fromLongitude AND :toLongitude"
    )
    fun getByLatLongBetween(
        fromLatitude: Int,
        toLatitude: Int,
        fromLongitude: Int,
        toLongitude: Int
    ): Flow<List<Tourating>>

}

