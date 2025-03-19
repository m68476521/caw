package com.morozco.mycaw.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozco.mycaw.network.ApiRepository
import com.morozco.mycaw.network.ItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCountries().fold({ throwable ->
                // TODO handle this error maybe show an spinner/hide it
                println("There was an error ${throwable.toString()}")
            }, { data ->
                countriesResponse.update {
                    data
                }
            })
        }
    }
}
