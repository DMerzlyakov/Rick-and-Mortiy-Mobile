package com.example.rickandmorty.character.presentation.list.mapper

import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterUi


fun CharacterDomain.toCharacterItem(): CharacterUi {
    return CharacterUi(
        this.id,
        this.name,
        this.status,
        this.species,
        this.gender,
        this.urlAvatar
    )
}