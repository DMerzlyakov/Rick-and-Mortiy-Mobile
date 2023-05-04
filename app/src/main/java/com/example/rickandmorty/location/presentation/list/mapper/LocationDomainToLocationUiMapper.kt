package com.example.rickandmorty.location.presentation.list.mapper

import com.example.rickandmorty.location.domain.list.model.LocationDomain
import com.example.rickandmorty.location.presentation.list.model.LocationUi
import javax.inject.Inject

class LocationDomainToLocationUiMapper @Inject constructor() {

    operator fun invoke(item: LocationDomain): LocationUi {
        return LocationUi(
            item.id,
            item.name,
            item.type,
            item.dimension,
        )
    }
}

