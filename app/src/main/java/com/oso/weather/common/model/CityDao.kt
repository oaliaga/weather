package com.oso.weather.common.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.oso.weather.common.entities.City
import com.oso.weather.common.utils.Constants

@Dao
interface CityDao {
    @Insert
    suspend fun addCity(city: City): Long

    @Update
    suspend fun updateCity(city:City): Int

    @Delete
    suspend fun deleteCity(city:City): Int

    @Query(value = "SELECT * FROM ${Constants.E_CITY}")
    suspend fun getAllCities(): List<City>

}