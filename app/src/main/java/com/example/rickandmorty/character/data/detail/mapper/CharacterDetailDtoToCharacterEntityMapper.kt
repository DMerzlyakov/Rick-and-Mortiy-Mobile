package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import com.example.rickandmorty.character.domain.detail.model.LocationDetailDomain
import javax.inject.Inject




class CharacterDetailDtoToCharacterEntityMapper @Inject constructor() {

    operator fun invoke(item: CharacterDetailDTO): CharacterEntity {

        val episodeList =
            if (item.episode.isEmpty()) emptyList<Int>()
            else item.episode.map { field -> field.split("/").last().toInt() }


        val idOrigin =
            if (item.origin.url == "") null else item.origin.url.split("/").last().toInt()

        val idLocation =
            if (item.location.url == "") null else item.location.url.split("/").last().toInt()

        return CharacterEntity(
            item.id, item.name, item.status, item.species, item.gender,
            item.image, idOrigin, item.origin.name, idLocation, item.location.name,
            episodeList
        )
    }
}