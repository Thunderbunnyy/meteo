package com.nourelhoudaeleuch.meteo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Coord")
data class LocationCoordEntity(
    @ColumnInfo(name = "lon")
    val lon: Double?,
    @ColumnInfo(name = "lat")
    val lat: Double?
)