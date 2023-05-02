package com.example.rickandmorty.location.data.detail.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.location.data.detail.local.ListIntConverter

@Entity(tableName = "locations")
@TypeConverters(ListIntConverter::class)
data class LocationEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Int>
)
