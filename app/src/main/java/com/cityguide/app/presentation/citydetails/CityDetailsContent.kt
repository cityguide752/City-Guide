package com.cityguide.app.presentation.citydetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cityguide.app.domain.model.City
import com.cityguide.app.presentation.tts.TTSManager

@Composable
fun CityDetailsContent(
    city: City
) {

    val context = LocalContext.current
    val ttsManager = remember { TTSManager(context) }

    DisposableEffect(Unit) {
        onDispose {
            ttsManager.shutdown()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = city.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium
        )

        Text(text = city.description)

        Spacer(modifier = Modifier.height(12.dp))

        Row {

            Button(
                onClick = {
                    ttsManager.speak(city.description)
                }
            ) {
                Text("Speak")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    ttsManager.stop()
                }
            ) {
                Text("Stop")
            }

        }

        if (city.attractions.isNotBlank()) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Attractions",
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = city.attractions)
        }

        if (city.culture.isNotBlank()) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Culture",
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = city.culture)
        }

        if (city.food.isNotBlank()) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Food",
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = city.food)
        }

    }

}