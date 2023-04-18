package com.example.rickandmorty.domain

data class CharactersFilter(
    val name: String,
    val species: String = "",
    val status: String = "",
    val gender: String = ""
)
