package com.example.rickandmorty.episode.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain

fun PagingData<EpisodeEntity>.toEpisodeDomain(): PagingData<EpisodeDomain> {
    return this.map { entity ->
        EpisodeDomain(
            entity.id,
            entity.name,
            entity.airDate,
            entity.episode
        )
    }
}
