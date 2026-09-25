package com.oso.weather.common.entities

data class WeatherResponse(
    val location: City = City(),
    val current: Current = Current()
)
