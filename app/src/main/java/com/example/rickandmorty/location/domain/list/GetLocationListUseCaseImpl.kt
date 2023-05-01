package com.example.rickandmorty.location.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.location.domain.list.model.LocationDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationListUseCaseImpl @Inject constructor(
    private val locationsListRepository: LocationsListRepository
) : GetLocationListUseCase {

    override suspend fun invoke(
        name: String, type: String,
        dimension: String
    ): Flow<PagingData<LocationDomainModel>> =
        locationsListRepository.getPagedLocations(
            name, type, dimension
        )

}