package com.oso.weather.cities.viewmodel

import com.oso.weather.common.entities.City
import kotlinx.coroutines.flow.StateFlow

interface ICitiesViewModel {

    fun getUiState(): StateFlow<CityUiState>
    fun showMap(city: City)
    fun clearMsg()
    fun deleteCity(city: City)

}