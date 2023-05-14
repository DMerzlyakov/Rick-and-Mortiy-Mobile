package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import javax.inject.Inject


class EpisodeCacheEntityToEpisodeDomainMapper @Inject constructor() {

    operator fun invoke(entity: EpisodeForDetailCacheEntity): EpisodeDomain {
        return EpisodeDomain(
                entity.id,
                entity.name,
                entity.airDate,
                entity.episode
            )
    }


}