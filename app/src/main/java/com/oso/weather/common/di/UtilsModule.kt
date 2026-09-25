package com.oso.weather.common.di

import com.oso.weather.common.utils.FormatUtils
import org.koin.dsl.module


val utilsModule = module{
    single { FormatUtils() }
}