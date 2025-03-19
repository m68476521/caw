package com.morozco.mycaw.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.morozco.mycaw.viewModel.CountryViewModel

@Composable
fun HomeScreen(viewModel: CountryViewModel) {

    val data by viewModel.countries.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getData()
    }

    val result = data
    LazyColumn(
        modifier = Modifier
            .height(100.dp)
    ) {

        items(result) { currentName ->
            Text(
                text = currentName.name.toString(),
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(6.dp),
                thickness = 2.dp
            )
        }
    }
}