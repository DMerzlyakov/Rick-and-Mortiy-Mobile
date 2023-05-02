package com.example.rickandmorty.character.domain.list

import androidx.paging.PagingData
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListByIdUseCaseImpl @Inject constructor(
    private val characterListRepository: CharacterListRepository
) : GetCharacterListByIdUseCase {

    override suspend fun invoke(
        characterLisstFilter: List<Int>
    ): Flow<PagingData<CharacterDomain>> =
        characterListRepository.getPagedCharacters(
            characterListFilter = characterLisstFilter
        )

}