package com.oso.weather.weather.model

import com.oso.weather.common.entities.WeatherResponse
import com.oso.weather.common.utils.Constants
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(Constants.PATH_V1)
    suspend fun searchWeatherByName(
        @Query(value = Constants.PARAM_API_KEY) key: String,
        @Query(value = Constants.PARAM_QUERY) name: String,
        @Query(value = Constants.PARAM_LANGUAGE) language: String
    ): WeatherResponse
}