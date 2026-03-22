package com.cityguide.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Home

@Serializable
object Auth

@Serializable
data class CityDetails(
    val cityName: String
)

@Serializable
object Settings