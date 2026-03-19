package com.cityguide.app.presentation.citydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.repository.CityRepositoryImpl
import com.cityguide.app.domain.usecase.GetCityDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityDetailsViewModel : ViewModel() {

    private val repository =
        CityRepositoryImpl(FirestoreCityDataSource())

    private val getCityDetailsUseCase =
        GetCityDetailsUseCase(repository)

    private val _uiState = MutableStateFlow(CityDetailsUiState())
    val uiState: StateFlow<CityDetailsUiState> = _uiState

    fun loadCity(cityName: String) {

        viewModelScope.launch {

            _uiState.value = CityDetailsUiState(isLoading = true)

            try {

                val city = getCityDetailsUseCase(cityName)

                _uiState.value = CityDetailsUiState(
                    city = city
                )

            } catch (e: Exception) {

                _uiState.value = CityDetailsUiState(
                    error = e.message
                )

            }

        }
    }
}