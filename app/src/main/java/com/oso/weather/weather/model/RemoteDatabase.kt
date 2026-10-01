package com.oso.weather.weather.model

import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.entities.WeatherResponse
import com.oso.weather.common.utils.Constants
import com.oso.weather.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDatabase(
    private val service: WeatherService
) {

    suspend fun searchWeatherByName(name: String, onResult: (WeatherResponse) -> Unit) =
        withContext(Dispatchers.IO){
            val result = service.searchWeatherByName(
                key = Constants.VAL_API_KEY,
                name = name,
                language = Constants.VAL_LANGUAGE)
            onResult(result)
        }

    suspend fun getWeatherByCoordinates(coordinates: String, onResult: (WeatherResponse) -> Unit) =
        withContext(Dispatchers.IO){
            val result = service.getWeatherByCoordinates(
                key = Constants.VAL_API_KEY,
                coordinates = coordinates,
                language = Constants.VAL_LANGUAGE)
            onResult(result)
        }

}