package com.cityguide.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cityguide.app.domain.model.City
import com.cityguide.app.ui.theme.CityGuideTheme

@Composable
fun CityCard(
    city: City,
    onClick: () -> Unit
) {

    val accentGradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,          // Blue
            MaterialTheme.colorScheme.secondaryContainer, // Green
            MaterialTheme.colorScheme.tertiary            // Gold
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .clickable { onClick() },

        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),

        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        )   {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(18.dp)
            ) {

                Text(
                    text = city.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = city.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                )

            }

            Box(
                modifier = Modifier
                    .width(18.dp)
                    .fillMaxHeight()
                    .background(
                        brush = accentGradient,
                        shape = RoundedCornerShape(
                            topEnd = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun CityCardPreview() {

    val sampleCity = City(
        name = "Paris",
        description = "The city of lights known for the Eiffel Tower, art museums, cafes, and romantic atmosphere."
    )

    CityGuideTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CityCard(
                city = sampleCity,
                onClick = {}
            )
        }
    }
}