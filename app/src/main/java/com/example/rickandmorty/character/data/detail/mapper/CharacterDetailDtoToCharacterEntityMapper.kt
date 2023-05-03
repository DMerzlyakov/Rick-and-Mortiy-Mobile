package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity


fun CharacterDetailDTO.toCharacterEntity(): CharacterEntity {
    val idOrigin = if (this.origin.url == "") null else this.origin.url.split("/").last().toInt()
    val idLocation = if (this.location.url == "") null else this.location.url.split("/").last().toInt()

    return CharacterEntity(this.id,
        this.name,
        this.status,
        this.species,
        this.gender,
        this.image,
        idOrigin,
        this.origin.name,
        idLocation,
        this.location.name,
        this.episode.map { item -> item.split("/").last().toInt() })
}