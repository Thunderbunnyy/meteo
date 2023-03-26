package com.nourelhoudaeleuch.meteo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nourelhoudaeleuch.meteo.data.database.entity.CLOUDS_ID
import com.nourelhoudaeleuch.meteo.data.database.entity.Clouds
import com.nourelhoudaeleuch.meteo.data.database.entity.Coord
import com.nourelhoudaeleuch.meteo.data.database.entity.LOCATION_ID


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: Coord)

    @Query("select * from current_location where id = $LOCATION_ID")
    fun getLocation(): LiveData<Coord>

}