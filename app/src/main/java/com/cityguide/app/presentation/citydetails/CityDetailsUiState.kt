package com.cityguide.app.presentation.citydetails

import com.cityguide.app.domain.model.City

data class CityDetailsUiState(
    val isLoading: Boolean = false,
    val city: City? = null,
    val error: String? = null
)