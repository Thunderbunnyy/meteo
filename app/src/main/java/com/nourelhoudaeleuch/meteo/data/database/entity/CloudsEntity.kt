package com.nourelhoudaeleuch.meteo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Clouds")
data class CloudsEntity(
    @ColumnInfo(name = "all")
    var all: Int
)