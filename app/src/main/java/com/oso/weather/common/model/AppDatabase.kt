package com.oso.weather.common.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oso.weather.common.entities.City
import com.oso.weather.common.entities.Weather
import com.oso.weather.common.utils.Constants

@Database(entities = [City::class, Weather::class], version = Constants.DB_INIT_VERSION)
abstract  class AppDatabase : RoomDatabase(){
    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao

    abstract fun weatherCityDao(): WeatherCityDao

}