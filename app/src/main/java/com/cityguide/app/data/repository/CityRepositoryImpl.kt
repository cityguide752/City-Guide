package com.cityguide.app.data.repository

import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.remote.ApiClient
import com.cityguide.app.domain.model.City
import com.cityguide.app.domain.repository.CityRepository

class CityRepositoryImpl(
    private val firestoreDataSource: FirestoreCityDataSource
) : CityRepository {

    override suspend fun getCities(): List<City> {

        val cityNames = firestoreDataSource.getCities()

        return cityNames.map { cityName ->

            val response = ApiClient.apiService.getCitySummary(cityName)

            City(
                name = cityName,
                description = response.extract
            )
        }
    }

    override suspend fun getCityDetails(cityName: String): City {
        val response = ApiClient.apiService.getCitySummary(cityName)

        return City(
            name = response.title,
            description = response.extract
        )
    }
}