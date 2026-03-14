package com.cityguide.app.domain.usecase

import com.cityguide.app.domain.model.City
import com.cityguide.app.domain.repository.CityRepository

class GetCitiesUseCase(
    private val repository: CityRepository
) {

    suspend operator fun invoke(): List<City> {
        return repository.getCities()
    }

}