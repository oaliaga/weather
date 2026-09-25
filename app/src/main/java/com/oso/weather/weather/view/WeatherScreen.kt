package com.oso.weather.weather.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.oso.weather.R
import com.oso.weather.common.entities.City
import com.oso.weather.common.entities.WeatherCity
import com.oso.weather.common.model.getAllCityPreview
import com.oso.weather.common.model.weatherCityPreview
import com.oso.weather.ui.components.AntCoilImage
import com.oso.weather.ui.components.AntDropdownMenu
import com.oso.weather.ui.components.AntProgressFullScreen
import com.oso.weather.ui.components.AntSnackbar
import com.oso.weather.ui.components.AntTextTitle
import com.oso.weather.ui.theme.CommonPaddingDefault
import com.oso.weather.ui.theme.CommonPaddingMin
import com.oso.weather.ui.theme.CommonPaddingXLarge
import com.oso.weather.ui.theme.MessageVerticalSpace
import com.oso.weather.ui.theme.Typography
import com.oso.weather.ui.theme.WeatherTheme
import com.oso.weather.weather.viewmodel.WeatherUiState
import com.oso.weather.weather.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun WeatherView(
    modifier: Modifier,
    vm: WeatherViewModel = koinViewModel()
) {
    val uiState by vm.uiState.collectAsState()
    Box(modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(CommonPaddingDefault)
        ) {
            AntTextTitle(R.string.weather_title)
            WeatherInfoView(uiState.data)
            ActionsView(
                uiState=uiState,
                onSelect = {city->
                    
                },
                onSave = {}
            )
            AntSnackbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MessageVerticalSpace),
                msgRes = uiState.msgRes,
                onDismiss = { vm.clearMsg() }
            )
            SearchView { name ->
                vm.searchWeather(name)
            }
        }
        AntProgressFullScreen(visible = uiState.inProgress)
    }
}

@Composable
private fun WeatherInfoView(weatherCity: WeatherCity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${weatherCity.temp_c.toInt()}°",
            style = Typography.displayLarge
        )
        Text(
            text = weatherCity.name,
            style = Typography.headlineLarge
        )
        Text(
            text = weatherCity.country,
            style = Typography.bodyLarge
        )
        AntCoilImage(
            url = weatherCity.iconHttps,
            modifier = Modifier
                .size(CommonPaddingXLarge)
                .padding(top = CommonPaddingMin),
            shape = RectangleShape
        )
        Text(
            text = weatherCity.description,
            style = Typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = if (weatherCity.name.isEmpty()) "" else "${weatherCity.wind_kph} km/h",
            style = Typography.bodyLarge
        )
    }

}

@Composable
private fun SearchView(onSearch: (String) -> Unit) {
    var cityValue by rememberSaveable { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CommonPaddingMin)
    ) {
        OutlinedTextField(
            value = cityValue,
            onValueChange = { cityValue = it },
            label = { Text(text = stringResource(R.string.cities_hint_search_city)) }
        )
        FilledIconButton(onClick = { onSearch(cityValue) }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "lupa")
        }
    }
}

@Composable
private fun ActionsView(
    uiState: WeatherUiState,
    onSelect:(City)->Unit,
    onSave:()->Unit
){

    Row(
        horizontalArrangement = Arrangement.spacedBy(CommonPaddingMin)
    ){
        AntDropdownMenu<City> (
            items=getAllCityPreview(),
            labelRes=R.string.cities_city,
            onSelect={ city->
                onSelect(city)
            }
        )
        OutlinedIconButton(
            onClick = {onSave()},
            enabled = uiState.data.name.isNotBlank()
        ) {
            Icon(
                imageVector = Icons.Default.CloudDownload, contentDescription = "lupa"
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun WeatherInfoPreview() {
    WeatherTheme {
        WeatherInfoView(weatherCityPreview)
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    WeatherTheme {
        SearchView {}
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionsPreview() {
    WeatherTheme {
        ActionsView(WeatherUiState(), {}) {}
    }
}
