package com.cityguide.app.presentation.citydetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.local.CityDatabase
import com.cityguide.app.data.repository.CityRepositoryImpl
import com.cityguide.app.domain.usecase.GetCityDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityDetailsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database =
        CityDatabase.getDatabase(application)

    private val repository =
        CityRepositoryImpl(
            firestoreDataSource = FirestoreCityDataSource(),
            cityDao = database.cityDao(),
            context = application
        )

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