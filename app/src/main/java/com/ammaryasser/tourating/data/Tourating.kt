package com.ammaryasser.tourating.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


const val TOURATING_TABLE_NAME = "tourating_table"


@Entity(tableName = TOURATING_TABLE_NAME)
data class Tourating(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(name = "site_name")
    val siteName: String,
    val rating: Int,
    val review: String,
    val createdAt: Long = System.currentTimeMillis()

) : Serializable