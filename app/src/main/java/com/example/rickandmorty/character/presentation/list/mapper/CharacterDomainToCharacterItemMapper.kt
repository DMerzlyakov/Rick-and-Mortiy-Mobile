package com.example.rickandmorty.character.presentation.list.mapper

import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterItem

class CharacterDomainToCharacterItemMapper {

    operator fun invoke(item: CharacterDomain): CharacterItem {
        return CharacterItem(
            item.id,
            item.name,
            item.status,
            item.species,
            item.gender,
            item.urlAvatar
        )


    }
}