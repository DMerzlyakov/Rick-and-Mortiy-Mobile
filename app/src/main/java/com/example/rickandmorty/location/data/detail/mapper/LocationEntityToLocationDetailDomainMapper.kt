package com.example.rickandmorty.location.data.detail.mapper


import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import javax.inject.Inject

class LocationEntityToLocationDetailDomainMapper @Inject constructor() {

    operator fun invoke(item: LocationEntity): LocationDetailDomain {

        return LocationDetailDomain(
            item.id,
            item.name,
            item.type,
            item.dimension,
            item.residents
        )
    }
}