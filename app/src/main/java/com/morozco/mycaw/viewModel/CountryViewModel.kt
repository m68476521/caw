package com.morozco.mycaw.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morozco.mycaw.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            repository.getCountries()
        }
    }
}
