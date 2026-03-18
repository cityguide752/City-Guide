package com.cityguide.app.data.repository

import com.cityguide.app.BuildConfig
import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.remote.GeminiService
import com.cityguide.app.data.remote.buildCityPrompt
import com.cityguide.app.domain.model.City
import com.cityguide.app.domain.repository.CityRepository

class CityRepositoryImpl(
    private val firestoreDataSource: FirestoreCityDataSource
) : CityRepository {

    private val apiKey = BuildConfig.GEMINI_API_KEY

    override suspend fun getCities(): List<City> {

        val cityNames = firestoreDataSource.getCities()

        return cityNames.map {

            City(
                name = it,
                description = "Tap to explore information about $it"
            )

        }
    }

    override suspend fun getCityDetails(cityName: String): City {

        val request = buildCityPrompt(cityName)

        val response = GeminiService.api.generateContent(
            apiKey = apiKey,
            request = request
        )

        val text = response
            .candidates
            ?.firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?: "Information unavailable."

        return City(
            name = cityName,
            description = text
        )
    }
}