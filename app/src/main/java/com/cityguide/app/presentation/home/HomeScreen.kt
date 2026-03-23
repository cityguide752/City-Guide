package com.cityguide.app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cityguide.app.domain.model.City

@Composable
fun HomeScreen(
    onCityClick: (City) -> Unit
) {

    val context = LocalContext.current

    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(context)
    )

    val uiState by viewModel.uiState.collectAsState()
    val previewCity = uiState.previewCity

    when {

        uiState.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }

        }

        uiState.error != null -> {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = uiState.error ?: "Error")
            }

        }

        else -> {

            HomeContent(
                cities = uiState.cities,
                onCityClick = { city ->
                    viewModel.loadCityPreview(city.name)
                }
            )

        }
    }
    previewCity?.let { city ->

        AlertDialog(

            onDismissRequest = {
                viewModel.closePreview()
            },

            title = {
                Text(city.name)
            },

            text = {
                Text(city.description)
            },

            confirmButton = {

                Button(
                    onClick = {

                        viewModel.closePreview()
                        onCityClick(city)

                    }
                ) {
                    Text("Open Details")
                }

            },

            dismissButton = {

                Button(
                    onClick = {
                        viewModel.closePreview()
                    }
                ) {
                    Text("Close")
                }

            }

        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    val fakeCities = listOf(

        City(
            name = "Paris",
            description = "Paris is famous for the Eiffel Tower, art museums, and romantic culture."
        ),

        City(
            name = "Tokyo",
            description = "Tokyo is Japan's capital known for technology, food, and temples."
        ),

        City(
            name = "Rome",
            description = "Rome is a historic city known for the Colosseum and ancient ruins."
        )

    )

    HomeScreen(
        onCityClick = {}
    )

}