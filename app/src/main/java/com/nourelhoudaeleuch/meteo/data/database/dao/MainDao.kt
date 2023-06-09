package com.nourelhoudaeleuch.meteo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nourelhoudaeleuch.meteo.data.database.entity.TEMP_ID
import com.nourelhoudaeleuch.meteo.data.database.entity.Main

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherMain: Main)

    @Query("select * from temperature where id = $TEMP_ID")
    fun getTemperature(): LiveData<Main>

}