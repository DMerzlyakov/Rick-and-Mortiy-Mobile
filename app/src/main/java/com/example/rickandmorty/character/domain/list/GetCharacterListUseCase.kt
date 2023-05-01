package com.example.rickandmorty.character.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.character.domain.list.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface GetCharacterListUseCase {

    suspend operator fun invoke(
        name: String, status: String,
        species: String, gender: String
    ): Flow<PagingData<CharacterDomainModel>>

}