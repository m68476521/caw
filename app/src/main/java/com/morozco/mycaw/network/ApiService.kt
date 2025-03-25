package com.morozco.mycaw.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.morozco.mycaw.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    companion object {

        fun create(url: String): ApiService =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(createDateFormatter()))
                .client(createClient())
                .build().create(ApiService::class.java)

        fun createClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(provideLoggingInterceptor())
                .addInterceptor(RequestInterceptor())
                .build()
        }

        private fun createDateFormatter(): Gson =
            GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create()

        fun provideLoggingInterceptor() =
            HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            }

    }

    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<ItemResponse>
}