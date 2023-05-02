package com.example.rickandmorty.location.presentation.list.mapper

import com.example.rickandmorty.location.domain.list.model.LocationDomain
import com.example.rickandmorty.location.presentation.list.model.LocationUi


fun LocationDomain.toLocationUiModel(): LocationUi {
    return LocationUi(
        this.id,
        this.name,
        this.type,
        this.dimension,
    )
}
