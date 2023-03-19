package com.example.myapppagatodo.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BankApiClient {

    @Synchronized
    fun getApiService(): ApiService {
        return buildRetrofit(getEnvironmentPath()).create(ApiService::class.java)
    }

    private fun getEnvironmentPath(): String {
        return BASE_URL
    }

    private fun buildRetrofit(url: String): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(url)
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    companion object {
        private const val BASE_URL = "https://dev.obtenmas.com"
    }
}