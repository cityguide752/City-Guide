package com.cityguide.app.data.remote

fun buildCityPrompt(city: String): GeminiRequest {

    val prompt = """
        Provide travel information about $city.
        
        Include:
        Attractions:
        Culture:
        Food:
        
        Keep each section short.
    """.trimIndent()

    return GeminiRequest(
        contents = listOf(
            Content(
                parts = listOf(
                    Part(text = prompt)
                )
            )
        )
    )
}