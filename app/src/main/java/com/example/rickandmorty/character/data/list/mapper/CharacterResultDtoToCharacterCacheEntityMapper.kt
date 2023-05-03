package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.data.list.remote.model.CharacterResultsDTO


fun List<CharacterResultsDTO>.toCharacterCacheListEntity(): List<CharacterForDetailCacheEntity> {
    return this.map {
        CharacterForDetailCacheEntity(it.id,
            it.name,
            it.status,
            it.species,
            it.gender,
            it.image,
            )
    }
}