package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import javax.inject.Inject


class CharacterCacheEntityToCharacterDomainMapper @Inject constructor() {

    operator fun invoke(item: CharacterForDetailCacheEntity): CharacterDomain {

        return CharacterDomain(
            item.id,
            item.name,
            item.status,
            item.species,
            item.gender,
            item.urlAvatar
        )

    }
}

