package com.example.rickandmorty.location.presentation.detail.model

data class LocationDetailUi(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Int>
)
