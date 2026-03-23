package com.cityguide.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cityguide.app.domain.usecase.GetCitiesUseCase
import com.cityguide.app.domain.usecase.GetCityDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getCityDetailsUseCase: GetCityDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadCities()
    }

    private fun loadCities() {

        viewModelScope.launch {

            _uiState.value = HomeUiState(isLoading = true)

            try {

                val cities = getCitiesUseCase()

                _uiState.value = HomeUiState(
                    cities = cities
                )

            } catch (e: Exception) {

                _uiState.value = HomeUiState(
                    error = e.message
                )

            }

        }
    }

    fun loadCityPreview(cityName: String) {

        viewModelScope.launch {

            try {

                val city = getCityDetailsUseCase(cityName)

                _uiState.value = _uiState.value.copy(
                    previewCity = city
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    error = e.message
                )

            }

        }
    }

    fun closePreview() {

        _uiState.value = _uiState.value.copy(
            previewCity = null
        )

    }
}