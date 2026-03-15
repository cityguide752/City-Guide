package com.cityguide.app.data.remote

import com.google.gson.annotations.SerializedName

data class WikipediaResponse(

    @SerializedName("title")
    val title: String,

    @SerializedName("extract")
    val extract: String

)