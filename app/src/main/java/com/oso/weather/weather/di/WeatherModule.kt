package com.oso.weather.weather.di

import androidx.datastore.dataStore
import com.oso.weather.weather.domain.DataSource
import com.oso.weather.weather.model.LocalDatabase
import com.oso.weather.weather.model.RemoteDatabase
import com.oso.weather.weather.viewmodel.WeatherViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val weatherModule = module {

    single { RemoteDatabase(service = get()) }
    single { LocalDatabase(cityDao = get(), get(), weatherCityDao = get(), utils = get()) }
    single { DataSource(rdb = get(), ldb = get(), nUtils = get(), fUtils = get()) }
    viewModel { WeatherViewModel(ds = get()) }

}