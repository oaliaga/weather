package com.oso.weather.weather.viewmodel

import com.oso.weather.R
import com.oso.weather.common.entities.City
import com.oso.weather.common.entities.WeatherCity

data class WeatherUiState(

    val data: WeatherCity = WeatherCity(),
    val inProgress: Boolean = false,
    val msgRes: Int = R.string.msg_empty,
    val items: List<City> = emptyList()

)
