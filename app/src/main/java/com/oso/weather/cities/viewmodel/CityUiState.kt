package com.oso.weather.cities.viewmodel

import com.oso.weather.R
import com.oso.weather.common.entities.City

data class CityUiState(
    val items: List<City> = emptyList(),
    val inProgress: Boolean = false,
    val msgRes: Int = R.string.msg_empty
)