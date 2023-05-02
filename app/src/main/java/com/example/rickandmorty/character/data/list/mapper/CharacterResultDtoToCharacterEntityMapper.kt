package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.remote.model.CharacterResultsDTO


fun List<CharacterResultsDTO>.toCharacterListEntity(): List<CharacterEntity> {
    return this.map {
        val idOrigin = if (it.origin.url == "") null else it.origin.url.split("/").last().toInt()
        val idLocation = if (it.location.url == "") null else it.location.url.split("/").last().toInt()

        CharacterEntity(it.id,
            it.name,
            it.status,
            it.species,
            it.gender,
            it.image,
            idOrigin,
            it.origin.name,
            idLocation,
            it.location.name,
            it.episode.map { item -> item.split("/").last().toInt() })
    }
}