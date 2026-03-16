package com.cityguide.app.data.firestore

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreCityDataSource {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getCities(): List<String> {

        val snapshot = firestore
            .collection("cities")
            .get()
            .await()

        return snapshot.documents.map { it.id }

    }
}