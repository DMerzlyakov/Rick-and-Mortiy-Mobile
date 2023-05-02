package com.example.rickandmorty.location.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationListUseCaseImpl @Inject constructor(
    private val locationsListRepository: LocationsListRepository
) : GetLocationListUseCase {

    override suspend fun invoke(
        name: String, type: String,
        dimension: String
    ): Flow<PagingData<LocationDomain>> =
        locationsListRepository.getPagedLocations(
            name, type, dimension
        )

}