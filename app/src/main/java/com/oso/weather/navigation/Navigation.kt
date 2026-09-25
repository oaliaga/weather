package com.oso.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oso.weather.cities.view.CitiesScreen
import com.oso.weather.weather.view.WeatherView

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.WEATHER -> WeatherView(modifier)
                    Destination.CITIES -> CitiesScreen(modifier)
                }
            }

        }
    }
}