package com.example.rickandmorty.character.data.list

import android.util.Log
import androidx.paging.*
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.data.list.mapper.toCharacterDomainModel
import com.example.rickandmorty.character.data.list.paging.CharacterListCacheRemoteMediator
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
        Log.e("getPagedCharacters", "getPagedCharacters")
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = CharacterListRemoteMediator(
                characterListApi,
                characterListDao,
                name,
                status,
                species,
                gender,
            ),
            pagingSourceFactory = {
                characterListDao.getPagingCharacter(
                    name,
                    status,
                    species,
                    gender,
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.toCharacterDomainModel()
            }
    }

    override fun getPagedCharactersCache(characterListFilter: List<Int>) : Flow<PagingData<CharacterDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = characterListFilter.size,
                enablePlaceholders = true,
                initialLoadSize = characterListFilter.size
            ),
            remoteMediator = CharacterListCacheRemoteMediator(
                characterListApi,
                characterListDao,
                characterListFilter
            ),
            pagingSourceFactory = {
                characterListDao.getPagingCharacterCache(
                    characterListFilter,
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