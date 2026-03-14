package com.cityguide.app.domain.usecase

import com.cityguide.app.domain.model.City
import com.cityguide.app.domain.repository.CityRepository

class GetCityDetailsUseCase(
    private val repository: CityRepository
) {

    suspend operator fun invoke(cityName: String): City {
        return repository.getCityDetails(cityName)
    }

}