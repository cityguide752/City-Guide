package com.cityguide.app.data.repository

import android.content.Context
import com.cityguide.app.BuildConfig
import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.local.CityDao
import com.cityguide.app.data.local.CityEntity
import com.cityguide.app.data.remote.GeminiService
import com.cityguide.app.data.remote.buildCityPrompt
import com.cityguide.app.domain.model.City
import com.cityguide.app.domain.repository.CityRepository
import com.cityguide.app.utils.isInternetAvailable

class CityRepositoryImpl(
    private val firestoreDataSource: FirestoreCityDataSource,
    private val cityDao: CityDao,
    private val context: Context
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

        val cachedCity = cityDao.getCity(cityName)

        if (cachedCity != null) {

            return City(
                name = cachedCity.name,
                description = cachedCity.description,
                attractions = cachedCity.attractions,
                culture = cachedCity.culture,
                food = cachedCity.food
            )
        }

        if (!isInternetAvailable(context)) {

            return City(
                name = cityName,
                description = "This city information is not available offline. Connect to the internet to load it."
            )
        }

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

        val city = City(
            name = cityName,
            description = text
        )

        cityDao.insertCity(
            CityEntity(
                name = city.name,
                description = city.description,
                attractions = city.attractions,
                culture = city.culture,
                food = city.food
            )
        )

        return city
    }
}