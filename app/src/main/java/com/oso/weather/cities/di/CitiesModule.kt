package com.oso.weather.cities.di

import com.oso.weather.cities.model.LocalDatabaseCity
import com.oso.weather.cities.viewmodel.CitiesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val citiesModule = module {
    single { LocalDatabaseCity(get(), get()) }
    viewModel { CitiesViewModel(get(), get()) }
}
