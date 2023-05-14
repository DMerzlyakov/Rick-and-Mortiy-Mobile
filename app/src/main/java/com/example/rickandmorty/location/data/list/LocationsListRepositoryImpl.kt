package com.example.rickandmorty.location.data.list


import androidx.paging.*
import com.example.rickandmorty.location.data.list.local.LocationDao
import com.example.rickandmorty.location.data.list.mapper.LocationDtoToLocationEntityMapper
import com.example.rickandmorty.location.data.list.mapper.LocationEntityToLocationDomainMapper
import com.example.rickandmorty.location.data.list.paging.LocationRemoteMediator
import com.example.rickandmorty.location.data.list.remote.LocationListApi
import com.example.rickandmorty.location.domain.list.LocationsListRepository
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationsListRepositoryImpl @Inject constructor(
    private val locationListApi: LocationListApi,
    private val locationListDao: LocationDao,
    private val mapperToEntity: LocationDtoToLocationEntityMapper,
    private val mapperToDomain: LocationEntityToLocationDomainMapper
) : LocationsListRepository {

    override suspend fun getPagedLocations(
        name: String,
        type: String,
        dimension: String
    ): Flow<PagingData<LocationDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = LocationRemoteMediator(
                locationListApi,
                locationListDao,
                mapperToEntity,
                name,
                type,
                dimension
            ),
            pagingSourceFactory = { locationListDao.getPagingLocation(name, type, dimension) }
        ).flow
            .map { it.map { item -> mapperToDomain(item)}}
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}