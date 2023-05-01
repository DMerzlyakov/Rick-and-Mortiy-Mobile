package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.remote.model.CharactersDTO


fun CharactersDTO.toCharacterEntity(): List<CharacterEntity> {
    return this.results.map {
        CharacterEntity(it.id, it.name, it.status, it.species, it.gender, it.image)
    }
}