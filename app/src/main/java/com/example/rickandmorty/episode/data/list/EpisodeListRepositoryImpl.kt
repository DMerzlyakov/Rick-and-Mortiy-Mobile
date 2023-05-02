package com.example.rickandmorty.episode.data.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.mapper.toEpisodeDomain
import com.example.rickandmorty.episode.data.list.paging.EpisodeRemoteMediator
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi
import com.example.rickandmorty.episode.domain.list.EpisodeListRepository
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EpisodeListRepositoryImpl @Inject constructor(
    private val episodeListApi: EpisodeListApi,
    private val episodeDao: EpisodeDao

): EpisodeListRepository {
    override suspend fun getPagedEpisode(
        name: String,
        episode: String
    ): Flow<PagingData<EpisodeDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = EpisodeRemoteMediator(
                episodeListApi,
                episodeDao,
                name,
                episode,
            ),
            pagingSourceFactory = { episodeDao.getPagingEpisode(name, episode) }
        ).flow
            .map { it.toEpisodeDomain()}
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}