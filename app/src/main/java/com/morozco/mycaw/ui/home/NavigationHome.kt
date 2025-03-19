package com.morozco.mycaw.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.morozco.mycaw.ui.CountriesHome
import com.morozco.mycaw.viewModel.CountryViewModel

fun NavGraphBuilder.homeViewerScreen(viewModel: CountryViewModel) {
    composable<CountriesHome> {
        HomeScreen(viewModel)
    }
}