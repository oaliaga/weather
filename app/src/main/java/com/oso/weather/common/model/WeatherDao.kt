package com.oso.weather.common.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.oso.weather.common.entities.Weather
import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.utils.Constants

@Dao
interface WeatherDao {

    @Insert
    suspend fun addWeather(weather: Weather): Long

    @Update
    suspend fun updateWeather(weather: Weather): Int

    @Delete
    suspend fun deleteWeather(weather: Weather): Int

    @Query("SELECT * FROM ${Constants.E_WEATHER} " +
            "WHERE ${Constants.P_CITY_ID} = :cityId " +
            "LIMIT 1")
    suspend fun getWeatherByCityId(cityId: Long): Weather?

    @Query("SELECT * FROM ${Constants.E_WEATHER} " +
            "INNER JOIN ${Constants.E_CITY} " +
            "ON ${Constants.E_WEATHER}.cityId = ${Constants.E_CITY}.id " +
            "WHERE ${Constants.E_WEATHER}.cityId = :cityId " +
            "LIMIT 1")
    suspend fun getWeatherCityByCityId(cityId: Long): WeatherCity?

}