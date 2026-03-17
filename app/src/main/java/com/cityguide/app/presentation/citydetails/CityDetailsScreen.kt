package com.cityguide.app.presentation.citydetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cityguide.app.domain.model.City

@Composable
fun CityDetailsScreen(
    cityName: String
) {

    val city = City(
        name = cityName,
        description = "This is a placeholder description for $cityName. The real data will be fetched from Wikipedia API in the next commit.",
        attractions = "Famous landmarks and attractions in $cityName.",
        culture = "$cityName has a rich cultural heritage with traditions and festivals.",
        food = "$cityName is known for its local cuisine and popular dishes."
    )

    CityDetailsContent(city = city)

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