package com.oso.weather.weather.model

import com.oso.weather.common.entities.WeatherResponse
import com.oso.weather.common.model.weatherResponseTest
import com.oso.weather.common.utils.Constants
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RemoteDatabaseTest {

    private lateinit var mockService: WeatherService
    private lateinit var rdb: RemoteDatabase


    @Before
    fun setUp() {
        mockService = mock<WeatherService>()
        rdb = RemoteDatabase(mockService)
    }

    @Test
    fun searchWeatherByName() = runTest {
        val nameQuery = "CDMX"
        whenever(methodCall = mockService.searchWeatherByName(Constants.VAL_API_KEY, nameQuery, Constants.VAL_LANGUAGE))
            .thenReturn(weatherResponseTest)

        var result = WeatherResponse()
        rdb.searchWeatherByName(nameQuery) { result = it }

        assertNotNull(result)
        verify(mockService).searchWeatherByName(
            Constants.VAL_API_KEY, nameQuery,
            Constants.VAL_LANGUAGE
        )

    }

    @Test
    fun getWeatherByCoordinates() = runTest {
        val emptyCoordinate = "0.0, 0.0"
        whenever(methodCall = mockService.getWeatherByCoordinates(Constants.VAL_API_KEY, emptyCoordinate, Constants.VAL_LANGUAGE))
            .thenReturn(null)

        var result: WeatherResponse? = WeatherResponse()
        rdb.getWeatherByCoordinates(emptyCoordinate) { result = it }

        assertNull(result)
        verify(mockService).getWeatherByCoordinates(
            Constants.VAL_API_KEY, emptyCoordinate,
            Constants.VAL_LANGUAGE
        )

    }


}