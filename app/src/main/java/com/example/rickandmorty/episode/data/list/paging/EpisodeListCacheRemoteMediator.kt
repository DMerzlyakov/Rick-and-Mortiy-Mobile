package com.example.rickandmorty.episode.data.list.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import com.example.rickandmorty.episode.data.list.mapper.EpisodeResultDtoToEpisodeCacheEntityMapper
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi

@OptIn(ExperimentalPagingApi::class)
class EpisodeListCacheRemoteMediator(
    private val episodeListApi: EpisodeListApi,
    private val episodeDao: EpisodeDao,
    private val dtoToCacheEntityMapper: EpisodeResultDtoToEpisodeCacheEntityMapper,
    private val episodeListFilter: List<Int>
) : RemoteMediator<Int, EpisodeForDetailCacheEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeForDetailCacheEntity>
    ): MediatorResult {
        return try {

            val episodes = getEpisodesListByIdByRemote(episodeListFilter)
            episodeDao.saveCache(episodes)
            MediatorResult.Success(endOfPaginationReached = true)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getEpisodesListByIdByRemote(episodeListFilter: List<Int>): List<EpisodeForDetailCacheEntity> {
        val episodes = episodeListApi.getEpisodeListByIdList(episodeListFilter.toString()).body()
        return dtoToCacheEntityMapper(episodes!!)
    }
}