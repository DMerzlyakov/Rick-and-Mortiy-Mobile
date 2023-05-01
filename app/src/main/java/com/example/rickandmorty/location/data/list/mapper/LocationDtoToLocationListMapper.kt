package com.example.rickandmorty.location.data.list.mapper

import com.example.rickandmorty.character.data.list.remote.model.CharactersDTO
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.location.data.list.remote.model.LocationDTO
import com.example.rickandmorty.location.domain.list.model.LocationDomainModel

class LocationDtoToLocationListMapper {

    operator fun invoke(listItem: LocationDTO): List<LocationDomainModel> {
        return  listItem.results.map {
            LocationDomainModel(it.id, it.name, it.type, it.dimension)
        }


    }
}