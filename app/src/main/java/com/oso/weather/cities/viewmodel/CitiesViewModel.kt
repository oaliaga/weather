package com.oso.weather.cities.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.toUri

import com.oso.weather.R
import com.oso.weather.cities.model.LocalDatabaseCity
import com.oso.weather.common.entities.City
import com.oso.weather.common.utils.IntentUtils

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CitiesViewModel(
     val dbCity: LocalDatabaseCity,
     val intents: IntentUtils
) : ViewModel(), ICitiesViewModel {

    val _uiState = MutableStateFlow(CityUiState())
    override fun getUiState(): StateFlow<CityUiState> = _uiState.asStateFlow()

    init {
        getAllCitiesRealtime()
    }

    private fun getAllCitiesRealtime() {
        viewModelScope.launch {
            dbCity.getAllCitiesRealtime().collect { result ->
                if (result.isNotEmpty()) {
                    _uiState.update { it.copy(items = result) }
                } else {
                    _uiState.update {
                        it.copy(
                            items = emptyList(),
                            msgRes = R.string.cities_msg_empty_list
                        )
                    }
                }
            }
        }
    }

    override fun showMap(city: City) {
        intents.showMap(city.lat, city.lon, city.name)

    }

    override fun clearMsg() {
        viewModelScope.launch {
            _uiState.update { it.copy(msgRes = R.string.msg_empty) }
        }
    }

    override fun deleteCity(city: City) {
        executeAction {
            dbCity.deleteCityAndWeather(city) { success ->
                if (success) {
                    _uiState.update { it.copy(msgRes = R.string.cities_msg_delete_success) }
                } else {
                    _uiState.update { it.copy(msgRes = R.string.cities_msg_delete_error) }
                }
            }
        }
    }

    private  fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            _uiState.update { it.copy(inProgress = true) }
            try {
                block()
            } catch (e: Exception) {
                _uiState.update { it.copy(msgRes = R.string.weather_general_error) }
            } finally {
                _uiState.update { it.copy(inProgress = false) }
            }
        }
    }

}