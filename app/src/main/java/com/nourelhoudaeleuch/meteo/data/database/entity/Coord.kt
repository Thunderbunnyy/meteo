package com.nourelhoudaeleuch.meteo.data.database.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val LOCATION_ID = 0

@Entity(tableName = "current_location")
data class Coord(
    val lon: Double,
    val lat: Double
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = LOCATION_ID
}