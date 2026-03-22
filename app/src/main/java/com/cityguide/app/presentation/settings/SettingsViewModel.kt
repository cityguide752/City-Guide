package com.cityguide.app.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cityguide.app.data.local.CityDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database = CityDatabase.getDatabase(application)

    private val auth = FirebaseAuth.getInstance()

    fun clearCache() {

        viewModelScope.launch {
            database.cityDao().clearCache()
        }

    }

    fun logout() {
        auth.signOut()
    }
}