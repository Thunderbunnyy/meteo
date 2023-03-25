package com.nourelhoudaeleuch.meteo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_ID = 0

@Entity(tableName = "current_weather")
data class Main(
    val temp: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)
{
    @PrimaryKey(autoGenerate = false)
    var id:Int = CURRENT_ID
}