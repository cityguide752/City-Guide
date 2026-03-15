package com.cityguide.app.presentation.home

import com.cityguide.app.domain.model.City

data class HomeUiState(

    val isLoading: Boolean = false,

    val cities: List<City> = emptyList(),

    val error: String? = null

)