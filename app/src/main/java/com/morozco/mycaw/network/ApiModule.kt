package com.morozco.mycaw.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.morozco.mycaw.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
open class ApiModule {

    @Provides
    @Singleton
    open fun createClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    open fun provideApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create(createDateFormatter()))
            .client(okHttpClient)
            .build().create(ApiService::class.java)

    private fun createDateFormatter(): Gson =
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create()

    @Provides
    @Singleton
    open fun provideApiRepository(apiService: ApiService): ApiRepository = ApiRepository(apiService)

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
}

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