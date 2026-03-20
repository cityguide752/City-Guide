package com.cityguide.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(

    @PrimaryKey
    val name: String,

    val description: String,

    val attractions: String,

    val culture: String,

    val food: String

)