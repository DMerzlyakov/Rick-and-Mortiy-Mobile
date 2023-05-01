package com.example.rickandmorty.location.presentation.list.mapper

import com.example.rickandmorty.location.domain.list.model.LocationDomainModel
import com.example.rickandmorty.location.presentation.list.model.LocationUiModel


fun LocationDomainModel.toLocationUiModel(): LocationUiModel {
    return LocationUiModel(
        this.id,
        this.name,
        this.type,
        this.dimension,
    )
}
