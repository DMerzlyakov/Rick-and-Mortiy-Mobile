package com.example.rickandmorty.character.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCaseImpl @Inject constructor(
    private val characterListRepository: CharacterListRepository
) : GetCharacterListUseCase {


    override suspend fun invoke(
        name: String, status: String,
        species: String, gender: String
    ): Flow<PagingData<CharacterDomain>> =
        characterListRepository.getPagedCharacters(
            name, status,
            species, gender
        )

}