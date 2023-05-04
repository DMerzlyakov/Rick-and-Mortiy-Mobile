package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import com.example.rickandmorty.character.domain.detail.model.LocationDetailDomain
import javax.inject.Inject

class CharacterEntityToCharacterDetailDomainMapper @Inject constructor() {

    operator fun invoke(item: CharacterEntity): CharacterDetailDomain {

        val mOrigin = LocationDetailDomain(
            item.originName,
            item.originId
        )

        val mLocation = LocationDetailDomain(
            item.locationName,
            item.locationId
        )

        return CharacterDetailDomain(
            id = item.id,
            name = item.name,
            status = item.status,
            species = item.species,
            gender = item.gender,
            origin = mOrigin,
            urlAvatar = item.urlAvatar,
            location = mLocation,
            episodeIdList = item.episodes
        )
    }
}