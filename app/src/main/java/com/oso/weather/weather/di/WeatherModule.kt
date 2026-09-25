package com.oso.weather.weather.di

import com.oso.weather.weather.model.RemoteDatabase
import com.oso.weather.weather.viewmodel.WeatherViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val weatherModule = module {

    single { RemoteDatabase(service = get(), utils = get()) }
    viewModel { WeatherViewModel(get()) }

}