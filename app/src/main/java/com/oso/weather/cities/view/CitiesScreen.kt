package com.oso.weather.cities.view


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import com.oso.weather.R
import com.oso.weather.cities.model.LocalDatabaseCity
import com.oso.weather.cities.viewmodel.CitiesViewModel
import com.oso.weather.cities.viewmodel.ICitiesViewModel
import com.oso.weather.common.entities.City
import com.oso.weather.ui.components.AntDialogInfo
import com.oso.weather.ui.components.AntProgressFullScreen
import com.oso.weather.ui.components.AntSnackbar
import com.oso.weather.ui.components.AntTextTitle
import com.oso.weather.ui.theme.CommonPaddingXLarge
import org.koin.androidx.compose.koinViewModel


@Composable
fun CitiesView(
    modifier: Modifier,
    vm: ICitiesViewModel = koinViewModel<CitiesViewModel>()
) {
    val uiState by vm.getUiState().collectAsState()
    var openDialog by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf<City?>(null) }

    Box(modifier.fillMaxSize()) {
        Column {
            AntTextTitle(R.string.cities_title)

            if (uiState.items.isEmpty()) {
                Text(text = stringResource(R.string.cities_msg_empty_list),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = CommonPaddingXLarge),
                    textAlign = TextAlign.Center)
            } else {
                LazyColumn {
                    items(uiState.items.size) { index ->
                        val city = uiState.items[index]
                        ItemCityView(city = city,
                            onMap = {
                                vm.showMap(city)
                            },
                            onRemove = { city ->
                                selectedCity = city
                                openDialog = true
                            })
                    }
                }
            }
        }

        if (openDialog) {
            selectedCity?.let { city ->
                AntDialogInfo( info = stringResource(R.string.dialog_msg_warning),
                    titleRes = R.string.dialog_delete_title,
                    confirmRes = R.string.dialog_delete_confirm) { isDeleted ->
                    if (isDeleted) vm.deleteCity(city)
                    openDialog = false
                }
            }
        }

        AntSnackbar (modifier = Modifier.fillMaxSize(),
            msgRes = uiState.msgRes,
            colorBackground = Color.White,
            shape = CircleShape,
            onDismiss = { vm.clearMsg() })

        AntProgressFullScreen(visible = uiState.inProgress)


    }

}
