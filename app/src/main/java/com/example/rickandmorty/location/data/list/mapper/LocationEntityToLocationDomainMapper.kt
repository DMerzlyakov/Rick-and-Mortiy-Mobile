package com.example.rickandmorty.location.data.list.mapper

import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import javax.inject.Inject


class LocationEntityToLocationDomainMapper @Inject constructor(){
    operator fun invoke(entity: LocationEntity): LocationDomain {
        return LocationDomain(
                entity.id,
                entity.name,
                entity.type,
                entity.dimension
        )

    }
}

