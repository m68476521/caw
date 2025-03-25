package com.morozco.mycaw.network

import arrow.core.Either

open class ApiRepository(private val apiService: ApiService) {
    open suspend fun getCountries(): Either<Throwable, List<ItemResponse>> = Either.catch {
        apiService.getCountries()
    }
}