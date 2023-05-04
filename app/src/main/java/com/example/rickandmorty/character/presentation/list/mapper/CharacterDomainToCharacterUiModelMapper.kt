package com.example.rickandmorty.character.presentation.list.mapper

import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterUi
import javax.inject.Inject


class CharacterDomainToCharacterUiModelMapper @Inject constructor() {
    operator fun invoke(item: CharacterDomain): CharacterUi {
        return CharacterUi(
            item.id,
            item.name,
            item.status,
            item.species,
            item.gender,
            item.urlAvatar
        )
    }
}
