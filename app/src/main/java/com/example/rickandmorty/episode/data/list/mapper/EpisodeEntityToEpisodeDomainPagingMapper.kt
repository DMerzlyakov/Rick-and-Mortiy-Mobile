package com.example.rickandmorty.episode.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import javax.inject.Inject


class EpisodeEntityToEpisodeDomainPagingMapper @Inject constructor() {

    operator fun invoke(item: PagingData<EpisodeEntity>): PagingData<EpisodeDomain> {
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
