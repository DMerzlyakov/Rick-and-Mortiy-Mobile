package com.example.rickandmorty.location.presentation.list.mapper

import com.example.rickandmorty.location.domain.list.model.LocationDomainModel
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel

class LocationDomainToLocationUiModelMapper {

    operator fun invoke(item: LocationDomainModel): LocationUiModel {
        return LocationUiModel(
            item.id,
            item.name,
            item.type,
            item.dimension,
        )


    }
}