package com.example.rickandmorty.episode.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeListByIdUseCaseImpl @Inject constructor(
    private val episodeListRepository: EpisodeListRepository
) : GetEpisodeListByIdUseCase {

    override suspend operator fun invoke(
        idEpisodeList: List<Int>,
    ): Flow<PagingData<EpisodeDomain>> {
        return episodeListRepository.getPagedEpisodesById(idEpisodeList)
    }
}