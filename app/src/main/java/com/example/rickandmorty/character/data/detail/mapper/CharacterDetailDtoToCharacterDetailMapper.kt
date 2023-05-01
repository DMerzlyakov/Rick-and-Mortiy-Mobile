package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.character.domain.detail.model.CharacterDetail
import com.example.rickandmorty.character.domain.detail.model.LocationDetail
import javax.inject.Inject

class CharacterDetailDtoToCharacterDetailMapper @Inject constructor() {

    operator fun invoke(item: CharacterDetailDTO): CharacterDetail {

        return CharacterDetail(
            id = item.id,
            name = item.name,
            status = item.status,
            species = item.species,
            gender = item.gender,
            origin = LocationDetail(
                item.origin.name,
                if (item.origin.url.isEmpty()) null
                else item.origin.url.split("/").last().toInt()
            ),
            urlAvatar = item.image,
            location = LocationDetail(
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