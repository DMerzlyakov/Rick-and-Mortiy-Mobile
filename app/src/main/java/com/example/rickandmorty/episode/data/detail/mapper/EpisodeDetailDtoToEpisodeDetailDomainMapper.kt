package com.example.rickandmorty.episode.data.detail.mapper

import com.example.rickandmorty.episode.data.detail.remote.model.EpisodeDetailDTO
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain


fun EpisodeDetailDTO.toEpisodeDetailDomain(): EpisodeDetailDomain {

    var characterIdList = emptyList<Int>()
    if (this.characters.isNotEmpty())
        characterIdList = this.characters.map {
            it.split("/").last().toInt()
        }

    return EpisodeDetailDomain(
        this.id,
        this.name,
        this.air_date,
        this.episode,
        characterIdList
    )
}
