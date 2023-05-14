package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import javax.inject.Inject


class CharacterEntityToCharacterDomainMapper @Inject constructor() {

    operator fun invoke(entity: CharacterEntity): CharacterDomain {

        return CharacterDomain(
            entity.id,
            entity.name,
            entity.status,
            entity.species,
            entity.gender,
            entity.urlAvatar
        )

    }
}



