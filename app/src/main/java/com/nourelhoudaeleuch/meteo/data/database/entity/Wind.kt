package com.nourelhoudaeleuch.meteo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val WIND_ID = 0

@Entity(tableName = "wind")
data class Wind(
    val speed: Double,
    val deg: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = WIND_ID
}