package com.example.rickandmorty.character.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomainModel

fun PagingData<CharacterEntity>.toCharacterDomainModel(): PagingData<CharacterDomainModel> {
    return this.map { entity ->
        CharacterDomainModel(
            entity.id,
            entity.name,
            entity.status,
            entity.species,
            entity.gender,
            entity.urlAvatar
        )
    }
}
