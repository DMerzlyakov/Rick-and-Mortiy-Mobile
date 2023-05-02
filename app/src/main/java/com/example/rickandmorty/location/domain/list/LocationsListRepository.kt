package com.example.rickandmorty.location.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import kotlinx.coroutines.flow.Flow

interface LocationsListRepository {
    suspend fun getPagedLocations(
        name: String = "", type: String = "",
        dimension: String = ""
    ): Flow<PagingData<LocationDomain>>
}