package com.cityguide.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cityguide.app.domain.usecase.GetCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCitiesUseCase: GetCitiesUseCase
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
                    isLoading = false,
                    cities = cities
                )

            } catch (e: Exception) {

                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = e.message
                )

            }
        }
    }
}