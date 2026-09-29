package com.oso.weather.cities.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.oso.weather.common.entities.City
import com.oso.weather.common.model.cityPreview
import com.oso.weather.ui.theme.CommonPaddingListItem3Vertical
import com.oso.weather.ui.theme.CommonPaddingMin
import com.oso.weather.ui.theme.WeatherTheme
import com.oso.weather.ui.theme.Typography

@Composable
fun ItemCityView(
    city: City,
    onMap: (City) -> Unit,
    onRemove: (City) -> Unit) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onRemove(city)
            }
            it != SwipeToDismissBoxValue.EndToStart
        }
    )

    SwipeToDismissBox(state = swipeToDismissBoxState,
        modifier = Modifier.fillMaxSize(),
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            when(swipeToDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(CommonPaddingListItem3Vertical),
                        tint = Color.White)
                }
                else -> {}
            }
        }) {
        Card(modifier = Modifier.padding(CommonPaddingMin)) {
            Row {
                Text(text = city.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(CommonPaddingMin),
                    style = Typography.headlineSmall)

                IconButton(onClick = { onMap(city) }) {
                    Icon(Icons.Default.ArrowOutward, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemCityPreview() {
    WeatherTheme {
        ItemCityView(cityPreview, {}, {})
    }
}