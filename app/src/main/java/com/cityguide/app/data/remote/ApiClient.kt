package com.cityguide.app.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://en.wikipedia.org/api/rest_v1/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->

            val request = chain.request().newBuilder()
                .addHeader("User-Agent", "CityGuideApp/1.0 (Android)")
                .build()

            chain.proceed(request)
        }
        .build()

    val apiService: WikipediaApiService by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WikipediaApiService::class.java)
    }
}