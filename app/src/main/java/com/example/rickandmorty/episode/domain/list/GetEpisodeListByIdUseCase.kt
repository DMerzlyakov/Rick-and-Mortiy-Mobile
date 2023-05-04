package com.example.rickandmorty.episode.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow

interface GetEpisodeListByIdUseCase {

    suspend operator fun invoke(
        idEpisodeList: List<Int>,
    ): Flow<PagingData<EpisodeDomain>>

}