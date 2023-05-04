package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeResultsDTO
import javax.inject.Inject


class EpisodeResultDtoToEpisodeCacheEntityMapper @Inject constructor() {

    operator fun invoke(item: List<EpisodeResultsDTO>): List<EpisodeForDetailCacheEntity> {
        return item.map {
            EpisodeForDetailCacheEntity(it.id, it.name, it.air_date, it.episode)
        }
    }
}
