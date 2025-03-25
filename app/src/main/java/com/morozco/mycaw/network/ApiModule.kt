package com.morozco.mycaw.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request().newBuilder().url(url)
            .header("Accept", "application/json")
            .header("Content-type", "application/json")
            .build()
        return chain.proceed(newRequest)
    }
}