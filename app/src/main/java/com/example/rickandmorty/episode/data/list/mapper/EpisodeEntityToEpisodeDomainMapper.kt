package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import javax.inject.Inject


class EpisodeEntityToEpisodeDomainMapper @Inject constructor() {

    operator fun invoke(entity: EpisodeEntity): EpisodeDomain {
        return EpisodeDomain(
                entity.id,
                entity.name,
                entity.airDate,
                entity.episode
            )

    }
}
