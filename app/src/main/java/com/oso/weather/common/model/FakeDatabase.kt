package com.oso.weather.common.model

import com.oso.weather.common.entities.City
import com.oso.weather.common.entities.WeatherCity


val cityPreview = City(0,"Lima", "Perú", 1.2118, -2.1921)

fun getAllCityPreview() = listOf(
    City(1,"Cdmx", "México", 19.4334565, -99.1331708),
    City(2,"Madrid", "España", 40.416775, -3.703790),
    cityPreview
)


val weatherCityPreview = WeatherCity(
    31f, "Vientos fuertes",
    22.5f, "", "Lima", "Perú"
)

fun getAllWeatherCityPreview() = listOf(
    WeatherCity(
        21f, "Nublado",
        12.5f, "", "CDMX", "México"
    ),
    WeatherCity(
        21f, "Soleado",
        6f, "", "Madrid", "España"
    ),
    weatherCityPreview
)