package com.example.rickandmorty.location.data.list.mapper

import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.data.list.remote.model.LocationDTO
import javax.inject.Inject

class LocationDtoToLocationEntityMapper @Inject constructor(){
    operator fun invoke(item: LocationDTO): List<LocationEntity> {
        return item.results.map {
            LocationEntity(it.id, it.name, it.type, it.dimension, it.residents.map { item ->
                item.split("/").last().toInt()
            })
        }
    }
}
