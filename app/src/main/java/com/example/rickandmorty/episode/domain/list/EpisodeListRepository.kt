package com.example.rickandmorty.episode.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow

interface EpisodeListRepository {
    suspend fun getPagedEpisode(
        name: String = "", episode: String = "",
    ): Flow<PagingData<EpisodeDomain>>
}