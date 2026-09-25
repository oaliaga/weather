package com.oso.weather.weather.model

import com.oso.weather.common.entities.City
import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.model.CityDao
import com.oso.weather.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatabase(private val cityDao: CityDao,  private val utils: FormatUtils) {

    suspend fun getAllCities(onResult: (List<City>) -> Unit) = withContext(Dispatchers.IO){
        onResult(cityDao.getAllCities())
    }

    suspend fun addWeatherAndCity(weatherCity: WeatherCity, onResult: (Boolean) -> Unit) =
        withContext(Dispatchers.IO) {
            val city = utils.weatherCityToCity(weatherCity)
            val weather = utils.weatherCityToWeather(weatherCity)
            val result = cityDao.addCity(city)
            //val tempResult = weatherDao.addWeather(weather)*/
            //val result = weatherCityDao.addCityAndWeather(city, weather)
            onResult(result > 0)
        }

}