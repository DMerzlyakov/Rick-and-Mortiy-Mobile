package com.example.rickandmorty.episode.data.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.mapper.*
import com.example.rickandmorty.episode.data.list.paging.EpisodeListCacheRemoteMediator
import com.example.rickandmorty.episode.data.list.paging.EpisodeListRemoteMediator
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi
import com.example.rickandmorty.episode.domain.list.EpisodeListRepository
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EpisodeListRepositoryImpl @Inject constructor(
    private val episodeListApi: EpisodeListApi,
    private val episodeDao: EpisodeDao,
    private val entityCacheToDomainPagingMapper: EpisodeCacheEntityToEpisodeDomainPaginMapper,
    private val entityToDomainPagingMapper: EpisodeEntityToEpisodeDomainPagingMapper,
    private val dtoToEntityMapper: EpisodeDtoToEpisodeEntityMapper,
    private val dtoToCacheEntityMapper: EpisodeResultDtoToEpisodeCacheEntityMapper

) : EpisodeListRepository {
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
            remoteMediator = EpisodeListRemoteMediator(
                episodeListApi,
                episodeDao,
                dtoToEntityMapper,
                name,
                episode,
            ),
            pagingSourceFactory = { episodeDao.getPagingEpisode(name, episode) }
        ).flow
            .map { entityToDomainPagingMapper(it) }
    }

    override suspend fun getPagedEpisodesById(episodeIdList: List<Int>): Flow<PagingData<EpisodeDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = episodeIdList.size,
                enablePlaceholders = true,
                initialLoadSize = episodeIdList.size
            ),
            remoteMediator = EpisodeListCacheRemoteMediator(
                episodeListApi,
                episodeDao,
                dtoToCacheEntityMapper,
                episodeIdList
            ),
            pagingSourceFactory = { episodeDao.getPagingEpisodeCache(episodeIdList) }
        ).flow
            .map { entityCacheToDomainPagingMapper(it) }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}