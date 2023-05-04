package com.example.rickandmorty.character.data.list.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import javax.inject.Inject


class CharacterEntityToCharacterDomainPagingMapper @Inject constructor() {

    operator fun invoke(item: PagingData<CharacterEntity>): PagingData<CharacterDomain> {

        return item.map { entity ->
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
}



