package com.example.rickandmorty.location.domain.list.model

data class LocationFilter(
    val name: String,
    val type: String = "",
    val dimension: String = ""
)
