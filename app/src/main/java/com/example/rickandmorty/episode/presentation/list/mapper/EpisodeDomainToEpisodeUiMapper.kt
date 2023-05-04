package com.example.rickandmorty.episode.presentation.list.mapper

import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi


fun EpisodeDomain.toEpisodeUiModel(): EpisodeUi {
    return EpisodeUi(
        this.id,
        this.name,
        this.airDate,
        this.episode
    )
}
