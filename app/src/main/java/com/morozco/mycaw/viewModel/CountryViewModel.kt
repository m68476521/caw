package com.morozco.mycaw.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozco.mycaw.network.ApiRepository
import com.morozco.mycaw.network.ItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val countriesResponse = MutableStateFlow<List<ItemResponse>>(emptyList())
    val countries: StateFlow<List<ItemResponse>> = countriesResponse.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            countriesResponse.update {
                repository.getCountries()
            }
        }
    }
}
