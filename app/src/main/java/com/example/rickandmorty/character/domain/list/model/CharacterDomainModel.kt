package com.example.rickandmorty.character.domain.list.model

data class CharacterDomainModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlAvatar: String
)
