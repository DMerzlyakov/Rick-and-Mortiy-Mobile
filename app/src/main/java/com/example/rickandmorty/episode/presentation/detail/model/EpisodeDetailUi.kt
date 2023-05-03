package com.example.rickandmorty.episode.presentation.detail.model

data class EpisodeDetailUi(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Int>
)
