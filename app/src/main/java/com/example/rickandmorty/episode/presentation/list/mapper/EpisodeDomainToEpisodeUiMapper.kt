package com.example.rickandmorty.episode.presentation.list.mapper

import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import com.example.rickandmorty.location.presentation.list.model.LocationUi


fun EpisodeDomain.toEpisodeUiModel(): EpisodeUi {
    return EpisodeUi(
        this.id,
        this.name,
        this.airDate,
        this.episode
    )
}
