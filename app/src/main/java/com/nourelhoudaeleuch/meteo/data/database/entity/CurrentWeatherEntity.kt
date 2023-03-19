package com.nourelhoudaeleuch.meteo.data.database.entity

import androidx.room.*

const val CURRENT_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity (
    @ColumnInfo(name = "visibility")
    var visibility: Int?,
    @ColumnInfo(name = "timezone")
    var timezone: Int?,
    @Embedded
    var main: MainEntity?,
    @Embedded
    var clouds: CloudsEntity?,
    @ColumnInfo(name = "dt")
    var dt: Long?,
    @ColumnInfo(name = "name")
    val name: String?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = CURRENT_ID,
    @ColumnInfo(name = "base")
    val base: String?,

    )
