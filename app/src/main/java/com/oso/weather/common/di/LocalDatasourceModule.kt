package com.oso.weather.common.di

import android.app.Application
import androidx.room.Room
import com.oso.weather.common.model.AppDatabase
import com.oso.weather.common.model.CityDao
import com.oso.weather.common.model.WeatherCityDao
import com.oso.weather.common.model.WeatherDao
import com.oso.weather.common.utils.Constants
import org.koin.dsl.module


fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        Constants.DB_NAME)
        .build()
}

fun provideCityDao(database: AppDatabase): CityDao = database.cityDao()

fun provideWeatherDao(database: AppDatabase): WeatherDao = database.weatherDao()

fun provideWeatherCityDao(database: AppDatabase): WeatherCityDao = database.weatherCityDao()

val localDatasourceModule = module {
    single { provideCityDao(get()) }
    single { provideWeatherDao(get()) }
    single { provideWeatherCityDao(get()) }
    single { provideDatabase(get()) }
}