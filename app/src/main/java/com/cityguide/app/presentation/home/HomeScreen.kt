package com.cityguide.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cityguide.app.domain.model.City
import com.cityguide.app.presentation.tts.TTSManager
import com.cityguide.app.ui.theme.CityGuideTheme

@Composable
fun HomeScreen(
    onCityClick: (City) -> Unit,
    onSettingsClick: () -> Unit
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
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
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
                },
                onSettingsClick = onSettingsClick
            )

        }
    }

    val ttsManager = remember { TTSManager(context) }
    var isSpeaking by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            ttsManager.shutdown()
        }
    }

    previewCity?.let { city ->

        AlertDialog(

            onDismissRequest = {
                ttsManager.stop()
                isSpeaking = false
                viewModel.closePreview()
            },

            title = {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = city.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            IconButton(
                                onClick = {

                                    if (isSpeaking) {
                                        ttsManager.stop()
                                    } else {
                                        ttsManager.speak(city.description)
                                    }

                                    isSpeaking = !isSpeaking
                                }
                            ) {

                                Text(
                                    text = if (isSpeaking) "🔇" else "🔊"
                                )

                            }

                        }

                    }

                }

            },

            text = {

                Text(
                    text = city.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            },

            confirmButton = {

                Button(
                    onClick = {
                        ttsManager.stop()
                        isSpeaking = false
                        viewModel.closePreview()
                        onCityClick(city)
                    }
                ) {
                    Text("Open Details")
                }

            },

            dismissButton = {

                TextButton(
                    onClick = {
                        ttsManager.stop()
                        isSpeaking = false
                        viewModel.closePreview()
                    }
                ) {
                    Text("Close")
                }

            },

            containerColor = Color.White,
            tonalElevation = 0.dp
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

    CityGuideTheme {
        HomeContent(
            cities = fakeCities,
            onCityClick = {},
            onSettingsClick = {}
        )
    }
}