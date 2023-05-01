package com.example.rickandmorty.location.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.location.domain.list.model.LocationDomainModel
import kotlinx.coroutines.flow.Flow

interface GetLocationListUseCase {

    suspend operator fun invoke(
        name: String, type: String,
        dimension: String
    ): Flow<PagingData<LocationDomainModel>>

}