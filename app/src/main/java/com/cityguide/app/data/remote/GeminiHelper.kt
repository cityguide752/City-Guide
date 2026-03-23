package com.cityguide.app.data.remote

fun buildCityPrompt(city: String): GeminiRequest {

    val prompt = """
        Provide travel information about $city.

        Format the response exactly like this:

        Description:
        A short 2-3 sentence overview of the city.

        Attractions:
        List 3-5 major attractions.

        Culture:
        Brief cultural highlights.

        Food:
        Popular local foods.

        Keep everything concise.
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