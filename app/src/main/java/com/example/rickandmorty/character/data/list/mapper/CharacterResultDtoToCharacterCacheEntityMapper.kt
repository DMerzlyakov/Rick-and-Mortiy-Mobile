package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.data.list.remote.model.CharacterResultsDTO
import javax.inject.Inject


class CharacterResultDtoToCharacterCacheEntityMapper @Inject constructor() {

    operator fun invoke(item: List<CharacterResultsDTO>): List<CharacterForDetailCacheEntity> {

        return item.map {
            CharacterForDetailCacheEntity(
                it.id,
                it.name,
                it.status,
                it.species,
                it.gender,
                it.image,
            )
        }
    }
}

