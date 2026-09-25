package com.oso.weather.weather.model

import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.utils.Constants
import com.oso.weather.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase(
    private val service: WeatherService,
    private val utils: FormatUtils
) {



    suspend fun searchWeatherByName(name: String, onResult: (WeatherCity?) -> Unit) =
        withContext(Dispatchers.IO) {
            try {
                val result = service.searchWeatherByName(
                    key = Constants.VAL_API_KEY,
                    name = name,
                    language = Constants.VAL_LANGUAGE
                )
                onResult(utils.responseToWeatherCity(result))
            } catch (e: Exception) {
                onResult(null)
            }
        }

}