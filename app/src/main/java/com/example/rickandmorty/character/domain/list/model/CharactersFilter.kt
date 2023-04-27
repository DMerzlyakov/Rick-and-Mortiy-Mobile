package com.example.rickandmorty.character.domain.list.model

data class CharactersFilter(
    val name: String,
    val species: String = "",
    val status: String = "",
    val gender: String = ""
)
