package com.example.rickandmorty.character.domain.detail.model

data class CharacterDetailDomain(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: LocationDetailDomain,
    val urlAvatar: String,
    val location: LocationDetailDomain,
    val episodeIdList: List<Int>
)
