package com.example.rickandmorty.character.domain.detail

data class CharacterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: LocationDetail,
    val urlAvatar: String,
    val location: LocationDetail,
    val episodeIdList: List<Int>
)
