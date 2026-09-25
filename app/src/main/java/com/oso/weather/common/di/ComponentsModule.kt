package com.oso.weather.common.di

import android.app.Application
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import org.koin.dsl.module

private fun provideImageLoader(context: Application): ImageLoader {
    return ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.25)
                .build()
        }
        .build()
}

val componentsModule = module {
    single { provideImageLoader(get()) }
}