package com.morozco.mycaw.network

import arrow.core.Either
import javax.inject.Inject

open class ApiRepository @Inject constructor(private val apiService: ApiService) {
    open suspend fun getCountries(): Either<Throwable, List<ItemResponse>> = Either.catch {
        apiService.getCountries()
    }
}