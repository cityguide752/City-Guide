package com.cityguide.app.domain.repository

import com.cityguide.app.domain.model.City

interface CityRepository {

    suspend fun getCities(): List<City>

    suspend fun getCityDetails(cityName: String): City
}