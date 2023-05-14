package com.example.rickandmorty.location.data.list.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.location.data.list.local.LocationDao
import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.data.list.mapper.LocationDtoToLocationEntityMapper
import com.example.rickandmorty.location.data.list.remote.LocationListApi

@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator(
    private val locationApi: LocationListApi,
    private val locationDao: LocationDao,
    private val mapperToEntity: LocationDtoToLocationEntityMapper,
    private val name: String,
    private val type: String,
    private val dimension: String
) : RemoteMediator<Int, LocationEntity>() {

    private var pageIndex = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationEntity>
    ): MediatorResult {

        pageIndex =
            getPagedIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize

        return try {
            val locations = getLocationsByRemote(name, type, dimension)
            locationDao.save(locations)
            MediatorResult.Success(endOfPaginationReached = locations.size < limit)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getLocationsByRemote(
        name: String,
        type: String,
        dimension: String
    ): List<LocationEntity> {
        val body = locationApi.getAllLocation(pageIndex, name, type, dimension).body()
        return mapperToEntity(body!!)
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