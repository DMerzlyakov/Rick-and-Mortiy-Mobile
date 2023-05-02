package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import com.example.rickandmorty.character.domain.detail.model.LocationDetailDomain
import javax.inject.Inject

class CharacterDetailDtoToCharacterDetailDomainMapper @Inject constructor() {

    operator fun invoke(item: CharacterDetailDTO): CharacterDetailDomain {

        return CharacterDetailDomain(
            id = item.id,
            name = item.name,
            status = item.status,
            species = item.species,
            gender = item.gender,
            origin = LocationDetailDomain(
                item.origin.name,
                if (item.origin.url.isEmpty()) null
                else item.origin.url.split("/").last().toInt()
            ),
            urlAvatar = item.image,
            location = LocationDetailDomain(
                item.location.name,
                if (item.location.url.isEmpty()) null
                else item.location.url.split("/").last().toInt()

            ),
            episodeIdList = item.episode.map { value -> value.split("/").last().toInt() }
        )
    }


    private fun findIdFromList(listString: List<String>) {


    }
}