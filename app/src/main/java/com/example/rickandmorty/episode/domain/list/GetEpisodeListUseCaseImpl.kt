package com.example.rickandmorty.episode.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeListUseCaseImpl @Inject constructor(
    private val episodeListRepository: EpisodeListRepository
) : GetEpisodeListUseCase {
    override suspend fun invoke(name: String, episode: String): Flow<PagingData<EpisodeDomain>> {
        return episodeListRepository.getPagedEpisode(name, episode)
    }

}