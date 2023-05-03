package com.example.rickandmorty.episode.domain.detail.model

data class EpisodeDetailDomain(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Int>
)
