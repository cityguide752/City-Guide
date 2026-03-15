package com.cityguide.app.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaApiService {

    @GET("page/summary/{title}")
    suspend fun getCitySummary(
        @Path("title") cityName: String
    ): WikipediaResponse

}