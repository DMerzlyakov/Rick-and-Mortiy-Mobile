package com.example.rickandmorty.episode.data.detail.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain


fun EpisodeEntity.toEpisodeDetailDomain(): EpisodeDetailDomain {
    return EpisodeDetailDomain(
        this.id,
        this.name,
        this.airDate,
        this.episode,
        this.characters
    )
}
