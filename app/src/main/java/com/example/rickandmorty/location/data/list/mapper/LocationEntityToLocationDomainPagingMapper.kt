package com.example.rickandmorty.location.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.domain.list.model.LocationDomain

fun PagingData<LocationEntity>.toLocationDomain(): PagingData<LocationDomain> {
    return this.map { entity ->
        LocationDomain(
            entity.id,
            entity.name,
            entity.type,
            entity.dimension
        )
    }
}
