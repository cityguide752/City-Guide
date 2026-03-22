package com.cityguide.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Query("SELECT * FROM cities WHERE name = :cityName")
    suspend fun getCity(cityName: String): CityEntity?

    @Query("DELETE FROM cities")
    suspend fun clearCache()
}