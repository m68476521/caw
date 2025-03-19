package com.morozco.mycaw.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozco.mycaw.network.ApiRepository
import com.morozco.mycaw.network.ItemResponse
import com.morozco.mycaw.network.Status
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

    private val countriesResponse = MutableStateFlow<List<ItemResponse>>(
        listOf(
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse(),
            ItemResponse(), ItemResponse()
        )
    )
    val countries: StateFlow<List<ItemResponse>> = countriesResponse.asStateFlow()

    private val loading = MutableStateFlow(Status.UNKNOWN)
    val isLoading: StateFlow<Status> = loading.asStateFlow()

    fun getData() {
        loading.update {
           Status.IN_PROGRESS
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCountries().fold({ throwable ->
                println("There was an error ${throwable.toString()}")
                loading.update {
                    Status.FAIL
                }
            }, { data ->
                loading.update {
                    Status.READY
                }

                countriesResponse.update {
                    data
                }
            })
        }
    }
}
