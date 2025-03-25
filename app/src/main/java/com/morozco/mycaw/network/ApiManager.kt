package com.morozco.mycaw.network

object ApiManager {

    const val URL = "https://gist.githubusercontent.com"

    lateinit var countriesApi: ApiRepository

    fun createApi() {
        countriesApi = ApiRepository(ApiService.create(URL))
    }
}