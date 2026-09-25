package com.oso.weather.cities.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oso.weather.R

@Composable
fun CitiesScreen(modifier: Modifier){
    Text(text = "Cities", Modifier.fillMaxSize().padding(250.dp).background(color = Color.Red))
}