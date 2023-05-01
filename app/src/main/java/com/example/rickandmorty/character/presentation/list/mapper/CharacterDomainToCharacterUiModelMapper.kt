package com.example.rickandmorty.character.presentation.list.mapper

import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterUiModel


fun CharacterDomain.toCharacterItem(): CharacterUiModel {
    return CharacterUiModel(
        this.id,
        this.name,
        this.status,
        this.species,
        this.gender,
        this.urlAvatar
    )
}
class CharacterDomainToCharacterItemMapper {

    operator fun invoke(item: CharacterDomain): CharacterUiModel {
        return CharacterUiModel(
            item.id,
            item.name,
            item.status,
            item.species,
            item.gender,
            item.urlAvatar
        )


    }
}