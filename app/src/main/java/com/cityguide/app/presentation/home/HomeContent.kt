package com.cityguide.app.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cityguide.app.domain.model.City

@Composable
fun HomeContent(
    cities: List<City>,
    onCityClick: (City) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(cities) { city ->

            CityCard(
                city = city,
                onClick = { onCityClick(city) }
            )

        }

    }

}