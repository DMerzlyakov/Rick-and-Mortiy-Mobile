package com.example.rickandmorty.domain

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlAvatar: String
)
