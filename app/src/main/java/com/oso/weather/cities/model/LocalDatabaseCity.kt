package com.oso.weather.cities.model

import com.oso.weather.common.entities.City
import com.oso.weather.common.model.CityDao
import com.oso.weather.common.model.WeatherCityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatabaseCity (private val cityDao: CityDao,
                         private val weatherCityDao: WeatherCityDao){

    suspend fun getAllCitiesRealtime(): Flow<List<City>> = cityDao.getAllCitiesRealtime()

    suspend fun deleteCityAndWeather(city: City, onResult: (Boolean) -> Unit) =
        withContext(Dispatchers.IO){
            onResult(weatherCityDao.deleteCityAndWeather(city) > 0)
        }
}