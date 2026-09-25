package com.oso.weather.common.di

import com.oso.weather.common.utils.FormatUtils
import com.oso.weather.common.utils.NetworkUtils
import org.koin.dsl.module

val utilsModule = module {
    single { FormatUtils() }
    single { NetworkUtils(context = get()) }
}