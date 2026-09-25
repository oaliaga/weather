package com.oso.weather.weather.di

import com.oso.weather.weather.model.LocalDatabase
import com.oso.weather.weather.model.RemoteDatabase
import com.oso.weather.weather.viewmodel.WeatherViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val weatherModule = module {

    single { RemoteDatabase(service = get(), utils = get()) }
    single { LocalDatabase(cityDao = get(), get(), weatherCityDao = get(), utils = get()) }
    viewModel { WeatherViewModel(rbd = get(), ldb = get(), utils = get()) }

}