package com.morozco.mycaw.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morozco.mycaw.network.Status
import com.morozco.mycaw.ui.common.CardItem
import com.morozco.mycaw.ui.common.ShimmerListItem
import com.morozco.mycaw.viewModel.CountryViewModel

@Composable
fun HomeScreen(viewModel: CountryViewModel) {

    val data by viewModel.countries.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (isLoading != Status.READY) {
            viewModel.getData()
        }
    }

    val result = data
    LazyColumn(
        modifier = Modifier
            .height(400.dp)
    ) {

        items(result) { currentItem ->
            ShimmerListItem(
                isLoading = isLoading == Status.IN_PROGRESS,
                contentAfterLoading = {
                    CardItem(currentItem)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            )
        }
    }
}