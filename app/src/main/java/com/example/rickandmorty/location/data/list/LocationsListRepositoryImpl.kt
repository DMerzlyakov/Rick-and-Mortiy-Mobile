package com.example.rickandmorty.location.data.list


import androidx.paging.*
import com.example.rickandmorty.location.data.list.local.LocationDao
import com.example.rickandmorty.location.data.list.mapper.toLocationDomain
import com.example.rickandmorty.location.data.list.paging.LocationRemoteMediator
import com.example.rickandmorty.location.data.list.remote.LocationListApi
import com.example.rickandmorty.location.domain.list.LocationsListRepository
import com.example.rickandmorty.location.domain.list.model.LocationDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationsListRepositoryImpl @Inject constructor(
    private val locationListApi: LocationListApi,
    private val locationListDao: LocationDao
) : LocationsListRepository {

    override suspend fun getPagedLocations(
        name: String,
        type: String,
        dimension: String
    ): Flow<PagingData<LocationDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = LocationRemoteMediator(
                locationListApi,
                locationListDao,
                name,
                type,
                dimension
            ),
            pagingSourceFactory = { locationListDao.getPagingLocation(name, type, dimension) }
        ).flow
            .map { it.toLocationDomain() }
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}