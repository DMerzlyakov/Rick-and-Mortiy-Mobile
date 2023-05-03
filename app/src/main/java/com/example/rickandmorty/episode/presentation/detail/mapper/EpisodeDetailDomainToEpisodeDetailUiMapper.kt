package com.example.rickandmorty.episode.presentation.detail.mapper

import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import com.example.rickandmorty.episode.presentation.detail.model.EpisodeDetailUi


fun EpisodeDetailDomain.toEpisodeDetailUi(): EpisodeDetailUi {
    return EpisodeDetailUi(
        this.id,
        this.name,
        this.airDate,
        this.episode,
        this.characters
    )
}