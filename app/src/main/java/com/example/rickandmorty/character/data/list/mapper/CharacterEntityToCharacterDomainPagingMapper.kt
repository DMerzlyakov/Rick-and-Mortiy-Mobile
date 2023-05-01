package com.example.rickandmorty.character.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomain

fun PagingData<CharacterEntity>.toCharacterDomain(): PagingData<CharacterDomain> {
    return this.map { entity ->
        CharacterDomain(
            entity.id,
            entity.name,
            entity.status,
            entity.species,
            entity.gender,
            entity.urlAvatar
        )
    }
}
