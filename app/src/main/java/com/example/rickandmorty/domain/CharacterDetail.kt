package com.example.rickandmorty.domain

data class CharacterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlAvatar: String,
    val locationId: Int,
    val episodeIdList: List<Int>

)
