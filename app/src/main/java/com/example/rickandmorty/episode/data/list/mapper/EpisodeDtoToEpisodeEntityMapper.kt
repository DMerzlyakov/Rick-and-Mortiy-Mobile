package com.example.rickandmorty.episode.data.list.mapper

import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeDto


fun EpisodeDto.toEpisodeEntity(): List<EpisodeEntity> {
    return this.results.map {
        EpisodeEntity(it.id, it.name, it.air_date, it.episode)
    }
}