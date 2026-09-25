package com.oso.weather.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oso.weather.R
import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.utils.FormatUtils
import com.oso.weather.weather.model.LocalDatabase
import com.oso.weather.weather.model.RemoteDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val rbd: RemoteDatabase ,
    private val ldb: LocalDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    init{
        //searchWeather("Chile")
    }

    fun searchWeather(name: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(inProgress = true) }
            try {
                rbd.searchWeatherByName(name) { result ->
                    if (result != null) {
                        _uiState.update { it.copy(data = result) }
                    } else {
                        _uiState.update { it.copy(msgRes = R.string.weather_search_error) }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(msgRes = R.string.weather_general_error) }
            } finally {
                _uiState.update { it.copy(inProgress = false) }
            }
        }
    }

    fun saveWeatherCity(weatherCity: WeatherCity){
        viewModelScope.launch {
            _uiState.update { it.copy(inProgress = true) }
            try{
                ldb.addWeatherAndCity(weatherCity){success->
                    if(success){
                        _uiState.update { it.copy(msgRes = R.string.weather_local_save_success) }
                    }else{
                        _uiState.update { it.copy(msgRes = R.string.weather_local_save_success) }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(msgRes = R.string.weather_general_error) }
            } finally {
                _uiState.update { it.copy(inProgress = false) }
            }
        }

    }

    fun clearMsg(){
        viewModelScope.launch {
            _uiState.update { it.copy(msgRes = R.string.msg_empty) }
        }
    }
}