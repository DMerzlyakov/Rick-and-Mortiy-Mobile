package com.example.rickandmorty.episode.data.list.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.mapper.EpisodeDtoToEpisodeEntityMapper
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi

@OptIn(ExperimentalPagingApi::class)
class EpisodeListRemoteMediator(
    private val episodeApi: EpisodeListApi,
    private val episodeDao: EpisodeDao,
    private val dtoToEntityMapper: EpisodeDtoToEpisodeEntityMapper,
    private val name: String,
    private val episode: String,
) : RemoteMediator<Int, EpisodeEntity>() {

    private var pageIndex = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
    ): MediatorResult {

        pageIndex =
            getPagedIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        return try {

            val episodes = getEpisodesByRemote(name, episode)
            episodeDao.save(episodes)
            MediatorResult.Success(episodes.size < state.config.pageSize)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getEpisodesByRemote(name: String, episode: String): List<EpisodeEntity> {
        val episodes = episodeApi.getAllEpisode(pageIndex, name, episode).body()
        return dtoToEntityMapper(episodes!!)
    }

    private fun getPagedIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex

    }
}