package com.oso.weather.weather.domain

import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.entities.WeatherResponse
import com.oso.weather.common.model.cityNullIslandTest
import com.oso.weather.common.model.weatherCityNullIslandTest
import com.oso.weather.common.model.weatherResponseNullIslandTest
import com.oso.weather.common.utils.FormatUtils
import com.oso.weather.common.utils.NetworkUtils
import com.oso.weather.weather.model.LocalDatabase
import com.oso.weather.weather.model.RemoteDatabase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(ExperimentalCoroutinesApi::class)
class DataSourceTest : KoinTest {

    private val rdb: RemoteDatabase = mockk()
    private val ldb: LocalDatabase = mockk()
    private val nUtils: NetworkUtils = mockk()

    private val dataSource: DataSource by inject()

    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(
                modules = module {
                    single { FormatUtils() }
                    single { nUtils }
                    single { ldb }
                    single { rdb }
                    single { DataSource(get(), get(), get(), get()) }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun getWeatherByCityOnlineSuccess() = runTest {
        val city = cityNullIslandTest
        val expectedWeatherCity = weatherCityNullIslandTest
        every { nUtils.isOnline() } returns true
        coEvery {
            rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}", any())
        } coAnswers {
            val onResultTest = arg<(WeatherResponse) -> Unit>(1)
            onResultTest(weatherResponseNullIslandTest)
        }
        var result: WeatherCity? = null
        dataSource.getWeatherByCity(city) { weatherCity ->
            result = weatherCity
        }

        coVerify(exactly = 1) { rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}", any()) }
        coVerify(exactly = 0) { ldb.getWeatherCityByCityId(city.id, any()) }
        assertEquals(expectedWeatherCity, result)
    }


    @Test
    fun getWeatherByCityOfflineSuccess() = runTest {
        val city = cityNullIslandTest
        val expectedWeatherCity = weatherCityNullIslandTest

        every { nUtils.isOnline() } returns false
        coEvery {
            ldb.getWeatherCityByCityId(city.id, any())
        } coAnswers {
            val onResultTest = arg<(WeatherCity) -> Unit>(1)
            onResultTest(weatherCityNullIslandTest)
        }

        var result: WeatherCity? = null
        dataSource.getWeatherByCity(city) { weatherCity ->
            result = weatherCity
        }

        coVerify(exactly = 0) { rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}", any()) }
        coVerify(exactly = 1) { ldb.getWeatherCityByCityId(city.id, any()) }
        assertEquals(expectedWeatherCity, result)
    }


    @Test
    fun getWeatherByCityOnlineFails() = runTest {
        val city = cityNullIslandTest

        every { nUtils.isOnline() } returns true
        coEvery {
            rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}", any())
        } coAnswers {
            val onResultTest = arg<(WeatherResponse?) -> Unit>(1)
            onResultTest(null)
        }

        var result: WeatherCity? = WeatherCity()
        dataSource.getWeatherByCity(city) { weatherCity ->
            result = weatherCity
        }

        coVerify(exactly = 1) { rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}", any()) }
        coVerify(exactly = 0) { ldb.getWeatherCityByCityId(city.id, any()) }
        assertNull(result)
    }

    @Test
    fun getWeatherByCityOfflineFails() = runTest {
        val city = cityNullIslandTest

        every { nUtils.isOnline() } returns false
        coEvery {
            ldb.getWeatherCityByCityId(city.id, any())
        } coAnswers {
            val onResultTest = arg<(WeatherCity?) -> Unit>(1)
            onResultTest(null)
        }

        var result: WeatherCity? = WeatherCity()
        dataSource.getWeatherByCity(city) { weatherCity ->
            result = weatherCity
        }

        coVerify(exactly = 0) { rdb.getWeatherByCoordinates("${city.lat}, ${city.lon}", any()) }
        coVerify(exactly = 1) { ldb.getWeatherCityByCityId(city.id, any()) }
        assertNull(result)
    }


    @Test
    fun searchWeatherByNameCheckEmptyReturnsNull() = runTest {
        val emptyName = ""

        var result: WeatherCity? = WeatherCity()
        dataSource.searchWeatherByName(emptyName) { weatherCity ->
            result = weatherCity
        }

        coVerify(exactly = 0) { rdb.searchWeatherByName(emptyName, any()) }
        assertNull(result)
    }

}