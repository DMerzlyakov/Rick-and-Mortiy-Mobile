package com.example.rickandmorty.location.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.domain.list.model.LocationDomainModel

fun PagingData<LocationEntity>.toLocationDomain(): PagingData<LocationDomainModel> {
    return this.map { entity ->
        LocationDomainModel(
            entity.id,
            entity.name,
            entity.type,
            entity.dimension
        )
    }
}
