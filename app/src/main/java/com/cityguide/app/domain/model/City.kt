package com.cityguide.app.domain.model

data class City(
    val name: String,
    val description: String,
    val attractions: String = "",
    val culture: String = "",
    val food: String = ""
)