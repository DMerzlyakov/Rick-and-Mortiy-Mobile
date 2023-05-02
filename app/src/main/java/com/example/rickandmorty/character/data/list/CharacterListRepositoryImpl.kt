package com.example.rickandmorty.character.data.list

import androidx.paging.*
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.data.list.mapper.toCharacterDomainModel
import com.example.rickandmorty.character.data.list.paging.CharacterListRemoteMediator
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import com.example.rickandmorty.character.domain.list.CharacterListRepository
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterListRepositoryImpl @Inject constructor(
    private val characterListApi: CharacterListApi,
    private val characterListDao: CharacterListDao
) : CharacterListRepository {

    override suspend fun getPagedCharacters(
        name: String, status: String,
        species: String, gender: String,
        characterListFilter: List<Int>
    ): Flow<PagingData<CharacterDomain>> {

        val pageSize = if (characterListFilter.isEmpty()) {
            PAGE_SIZE
        } else {
            characterListFilter.size
        }


        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = true,
                initialLoadSize = pageSize
            ),
            remoteMediator = CharacterListRemoteMediator(
                characterListApi,
                characterListDao,
                name,
                status,
                species,
                gender,
                characterListFilter
            ),
            pagingSourceFactory = {
                characterListDao.getPagingCharacter(
                    name,
                    status,
                    species,
                    gender,
                    characterListFilter,
                    characterListFilter.size
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.toCharacterDomainModel()
            }
    }


    private companion object {
        const val PAGE_SIZE = 20
    }
}