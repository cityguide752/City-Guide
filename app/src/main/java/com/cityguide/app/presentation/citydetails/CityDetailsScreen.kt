package com.cityguide.app.presentation.citydetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cityguide.app.domain.model.City

@Composable
fun CityDetailsScreen(
    cityName: String
) {

    val viewModel: CityDetailsViewModel = viewModel()

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(cityName) {
        viewModel.loadCity(cityName)
    }

    when {

        uiState.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

        uiState.error != null -> {

            Text(text = uiState.error ?: "Error loading city")

        }

        uiState.city != null -> {

            CityDetailsContent(
                city = uiState.city!!
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun CityDetailsPreview() {

    val sampleCity = City(
        name = "Paris",
        description = "Paris is the capital city of France known for art, culture, and the Eiffel Tower.",
        attractions = "Eiffel Tower, Louvre Museum, Notre-Dame Cathedral",
        culture = "Paris is famous for art, fashion, and historic architecture.",
        food = "Croissants, baguettes, cheese, and French pastries."
    )

    CityDetailsContent(city = sampleCity)

}