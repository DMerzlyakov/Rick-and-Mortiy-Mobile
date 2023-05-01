package com.example.rickandmorty.character.data.list

import android.util.Log
import androidx.paging.*
import com.example.rickandmorty.character.data.list.local.CharactersListDao
import com.example.rickandmorty.character.data.list.paging.CharactersListRemoteMediator
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import com.example.rickandmorty.character.domain.list.CharacterListRepository
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterListRepositoryImpl @Inject constructor(
    private val characterListApi: CharacterListApi,
    private val charactersListDao: CharactersListDao
) : CharacterListRepository {

    override suspend fun getPagedCharacters(
        name: String, status: String,
        species: String, gender: String
    ): Flow<PagingData<CharacterDomain>> {

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = CharactersListRemoteMediator(
                characterListApi,
                charactersListDao,
                name,
                status,
                species,
                gender
            ),
            pagingSourceFactory = { charactersListDao.getPagingCharacter(name, status, species, gender) }
        ).flow
            .map { pagingData ->
                pagingData.map { entity ->
                    Log.e("ASDASD", entity.toString())
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

    private companion object {
        const val PAGE_SIZE = 20
    }
}