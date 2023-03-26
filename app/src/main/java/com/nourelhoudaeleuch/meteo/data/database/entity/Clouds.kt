package com.nourelhoudaeleuch.meteo.data.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

const val CLOUDS_ID = 0

@Entity(tableName = "clouds")
data class Clouds(
    val all: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CLOUDS_ID
}