package com.morozco.mycaw.network

import javax.inject.Inject

open class ApiRepository @Inject constructor(private val apiService: ApiService) {
    open suspend fun getCountries() = apiService.getCountries()
}