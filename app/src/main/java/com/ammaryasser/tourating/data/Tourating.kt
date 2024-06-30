package com.ammaryasser.tourating.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


const val TOURATING_TABLE_NAME = "tourating_table"


@Entity(tableName = TOURATING_TABLE_NAME)
data class Tourating(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val title: String? = null,
    val rating: Int,
    val review: String,
    val createdAt: Long = System.currentTimeMillis()

) : Serializable