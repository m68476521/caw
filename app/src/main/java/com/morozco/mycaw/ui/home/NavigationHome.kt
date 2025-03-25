package com.morozco.mycaw.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.morozco.mycaw.ui.CountriesHome

fun NavGraphBuilder.homeViewerScreen() {
    composable<CountriesHome> {
        HomeScreen()
    }
}