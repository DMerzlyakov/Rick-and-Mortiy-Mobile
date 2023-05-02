package com.example.rickandmorty.character.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface GetCharacterListByIdUseCase {

    suspend operator fun invoke(
        characterListFilter: List<Int>
    ): Flow<PagingData<CharacterDomain>>

}