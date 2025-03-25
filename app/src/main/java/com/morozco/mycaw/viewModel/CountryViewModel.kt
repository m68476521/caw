package com.morozco.mycaw.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozco.mycaw.network.ApiManager
import com.morozco.mycaw.network.ItemResponse
import com.morozco.mycaw.network.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    val countriesResponse = MutableLiveData<List<ItemResponse>>()

    private val loading = MutableStateFlow(Status.UNKNOWN)
    val isLoading: StateFlow<Status> = loading.asStateFlow()

    init {
        if (isLoading.value != Status.READY) {
            getData()
        }
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.update {
                Status.IN_PROGRESS
            }

            ApiManager.countriesApi.getCountries().fold({ throwable ->
                println("There was an error ${throwable.toString()}")
                loading.update {
                    Status.FAIL
                }
            }, { data ->
                loading.update {
                    Status.READY
                }
                countriesResponse.postValue(data)
            })
        }
    }
}
