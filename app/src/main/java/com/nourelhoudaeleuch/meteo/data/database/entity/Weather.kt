package com.nourelhoudaeleuch.meteo.data.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class Weather(
    val main: String,
    val description: String,
    val icon: String
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}