package com.cityguide.app.presentation.citydetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cityguide.app.domain.model.City
import com.cityguide.app.presentation.tts.TTSManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsContent(
    city: City,
    onBackClick: () -> Unit = {}
) {

    val context = LocalContext.current
    val ttsManager = remember { TTSManager(context) }

    DisposableEffect(Unit) {
        onDispose { ttsManager.shutdown() }
    }

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.tertiary
        )
    )

    Scaffold(
        topBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gradient)
            ) {

                TopAppBar(

                    title = {
                        Text(
                            text = city.name,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },

                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = androidx.compose.ui.graphics.Color.Transparent
                    )

                )

            }

        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "About ${city.name}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = city.description,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(Modifier.height(16.dp))

                    Row {

                        Button(
                            onClick = { ttsManager.speak(city.description) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text("Listen")
                        }

                        Spacer(Modifier.width(12.dp))

                        OutlinedButton(
                            onClick = { ttsManager.stop() }
                        ) {
                            Text("Stop")
                        }
                    }
                }
            }

            if (city.attractions.isNotBlank()) {
                SectionCard("Top Attractions", city.attractions)
            }

            if (city.culture.isNotBlank()) {
                SectionCard("Culture", city.culture)
            }

            if (city.food.isNotBlank()) {
                SectionCard("Local Food", city.food)
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun SectionCard(
    title: String,
    content: String
) {

    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.tertiary
        )
    )

    Spacer(Modifier.height(18.dp))

    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = content.replace("*", "•"),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Box(
                modifier = Modifier
                    .width(10.dp)
                    .fillMaxHeight()
                    .background(
                        brush = gradient,
                        shape = RoundedCornerShape(
                            topEnd = 18.dp,
                            bottomEnd = 18.dp
                        )
                    )
            )
        }
    }
}