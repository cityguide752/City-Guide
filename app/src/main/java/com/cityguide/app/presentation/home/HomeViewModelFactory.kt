package com.cityguide.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.repository.CityRepositoryImpl
import com.cityguide.app.domain.usecase.GetCitiesUseCase

class HomeViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val firestoreDataSource = FirestoreCityDataSource()

        val repository = CityRepositoryImpl(firestoreDataSource)

        val getCitiesUseCase = GetCitiesUseCase(repository)

        return HomeViewModel(getCitiesUseCase) as T
    }

}