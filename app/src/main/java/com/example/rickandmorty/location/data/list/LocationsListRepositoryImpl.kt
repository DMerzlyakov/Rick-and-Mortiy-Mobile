package com.example.rickandmorty.location.data.list


import android.util.Log
import androidx.paging.*
import com.example.rickandmorty.location.data.list.local.LocationDao
import com.example.rickandmorty.location.data.list.mapper.LocationDtoToLocationListMapper
import com.example.rickandmorty.location.data.list.paging.LocationRemoteMediator
import com.example.rickandmorty.location.data.list.remote.LocationListApi
import com.example.rickandmorty.location.domain.list.LocationsListRepository
import com.example.rickandmorty.location.domain.list.model.LocationDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class LocationsListRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val locationListApi: LocationListApi,
    private val locationListDao: LocationDao
) : LocationsListRepository {

    private val locationDtoToLocationListMapper = LocationDtoToLocationListMapper()
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
            remoteMediator = LocationRemoteMediator(locationListApi, locationListDao, name, type, dimension),
            pagingSourceFactory = {locationListDao.getPagingLocation(name, type, dimension)}
        ).flow
            .map { pagingData ->
                pagingData.map {entity ->
                    LocationDomainModel(entity.id, entity.name, entity.type, entity.dimension)
                }
            }
    }

    private suspend fun getLocations(
        pageIndex: Int, name: String = "", type: String = "",
        dimension: String = ""
    ): List<LocationDomainModel> =
        withContext(ioDispatcher) {
            Log.d("NETWORK", "Load location page $pageIndex - $name")
            val list = locationListApi.getAllLocation(pageIndex, name, type, dimension).body()

            return@withContext locationDtoToLocationListMapper(list!!)
        }

    private companion object {
        const val PAGE_SIZE = 20
    }
}