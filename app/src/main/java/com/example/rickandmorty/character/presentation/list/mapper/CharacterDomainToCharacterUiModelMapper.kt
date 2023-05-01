package com.example.rickandmorty.character.presentation.list.mapper

import com.example.rickandmorty.character.domain.list.model.CharacterDomainModel
import com.example.rickandmorty.character.presentation.list.model.CharacterUiModel


fun CharacterDomainModel.toCharacterItem(): CharacterUiModel {
    return CharacterUiModel(
        this.id,
        this.name,
        this.status,
        this.species,
        this.gender,
        this.urlAvatar
    )
}