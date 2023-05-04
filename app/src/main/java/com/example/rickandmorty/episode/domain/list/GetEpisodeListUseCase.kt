package com.example.rickandmorty.episode.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow

interface GetEpisodeListUseCase {

    suspend operator fun invoke(
        name: String, episode: String,
    ): Flow<PagingData<EpisodeDomain>>

}