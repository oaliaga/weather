package com.oso.weather.common.entities

data class Location(
    val name: String = "",
    val country: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val tz_id: String = ""
)
