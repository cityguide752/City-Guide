package com.cityguide.app.presentation.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cityguide.app.data.firestore.FirestoreCityDataSource
import com.cityguide.app.data.local.CityDatabase
import com.cityguide.app.data.repository.CityRepositoryImpl
import com.cityguide.app.domain.usecase.GetCitiesUseCase

class HomeViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val firestoreDataSource = FirestoreCityDataSource()

        val database = CityDatabase.getDatabase(context)

        val repository = CityRepositoryImpl(
            firestoreDataSource = firestoreDataSource,
            cityDao = database.cityDao()
        )

        val getCitiesUseCase = GetCitiesUseCase(repository)

        return HomeViewModel(getCitiesUseCase) as T
    }
}