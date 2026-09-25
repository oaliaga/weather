package com.oso.weather

import android.app.Application
import com.oso.weather.common.di.componentsModule
import com.oso.weather.common.di.localDatasourceModule
import com.oso.weather.common.di.utilsModule
import com.oso.weather.weather.di.remoteDataSourceModule
import com.oso.weather.weather.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                weatherModule,
                utilsModule,
                remoteDataSourceModule,
                localDatasourceModule,
                componentsModule,
                //citiesModule
            )
        }
    }
}