package com.example.rickandmorty.episode.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain

fun PagingData<EpisodeForDetailCacheEntity>.toEpisodeDomain(): PagingData<EpisodeDomain> {
    return this.map { entity ->
        EpisodeDomain(
            entity.id,
            entity.name,
            entity.airDate,
            entity.episode
        )
    }
}
