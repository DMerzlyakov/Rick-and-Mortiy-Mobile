package com.example.rickandmorty.episode.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import javax.inject.Inject


class EpisodeCacheEntityToEpisodeDomainPaginMapper @Inject constructor() {

    operator fun invoke(item: PagingData<EpisodeForDetailCacheEntity>): PagingData<EpisodeDomain> {
        return item.map { entity ->
            EpisodeDomain(
                entity.id,
                entity.name,
                entity.airDate,
                entity.episode
            )
        }
    }


}