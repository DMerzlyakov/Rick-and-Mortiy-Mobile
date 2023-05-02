package com.example.rickandmorty.location.domain.detail.model

data class LocationDetailDomain(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Int>
)
