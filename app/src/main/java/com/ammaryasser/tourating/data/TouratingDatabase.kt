package com.ammaryasser.tourating.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Tourating::class],
    version = 1,
    exportSchema = false
)
abstract class TouratingDatabase : RoomDatabase() {

    abstract fun touratingDao(): TouratingDao

    companion object {
        @Volatile
        private var Instance: TouratingDatabase? = null

        fun getDatabase(context: Context): TouratingDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, TouratingDatabase::class.java, "tourating_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}