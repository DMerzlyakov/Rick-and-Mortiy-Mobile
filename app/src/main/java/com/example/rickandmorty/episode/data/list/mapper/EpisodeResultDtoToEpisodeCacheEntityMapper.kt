package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeResultsDTO

fun List<EpisodeResultsDTO>.toEpisodeCacheEntity(): List<EpisodeForDetailCacheEntity> {
    return this.map {
        EpisodeForDetailCacheEntity(it.id, it.name, it.air_date, it.episode)
    }
}