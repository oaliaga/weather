package com.oso.weather.weather.domain

import com.oso.weather.common.entities.City
import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.utils.Constants
import com.oso.weather.common.utils.FormatUtils
import com.oso.weather.common.utils.NetworkUtils
import com.oso.weather.weather.model.LocalDatabase
import com.oso.weather.weather.model.RemoteDatabase


class DataSource(
    private val rdb: RemoteDatabase,
    private val ldb: LocalDatabase,
    private val nUtils: NetworkUtils,
    private val fUtils: FormatUtils
) {

    suspend fun getAllCities(onResult: (List<City>) -> Unit) = ldb.getAllCities { onResult(it) }

    suspend fun searchWeatherByName(name: String, onResult: (WeatherCity?) -> Unit) {
        try {
            if (name.isEmpty()) {
                onResult(null)
            } else {
                rdb.searchWeatherByName(name) { result ->
                    onResult(fUtils.responseToWeatherCity(result))
                }
            }
        } catch (e: Exception) {
            onResult(null)
        }
    }


    suspend fun addWeatherAndCity(weatherCity: WeatherCity, onResult: (Boolean) -> Unit) =
        ldb.addWeatherAndCity(weatherCity) { onResult(it) }

    suspend fun getWeatherByCity(city: City, onResult: (WeatherCity?) -> Unit) {
        try {
            if (nUtils.isOnline()) {
                //rdb.searchWeatherByName(city.name) { onResult(fUtils.responseToWeatherCity(it)) }
                rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}") { result ->
                    onResult(fUtils.responseToWeatherCity(result))
                }
            } else {
                ldb.getWeatherCityByCityId(city.id) { onResult(it) }
            }
        } catch (e: Exception) {
            onResult(null)
        }
    }

}