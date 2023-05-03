package com.example.rickandmorty.character.data.list.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.data.list.mapper.toCharacterCacheListEntity
import com.example.rickandmorty.character.data.list.remote.CharacterListApi

@OptIn(ExperimentalPagingApi::class)
class CharacterListCacheRemoteMediator(
    private val characterListApi: CharacterListApi,
    private val characterListDao: CharacterListDao,
    private val characterListFilter: List<Int>
) : RemoteMediator<Int, CharacterForDetailCacheEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterForDetailCacheEntity>
    ): MediatorResult {

        return try {

            val characters = getCharactersListByIdByRemote(characterListFilter)

            characterListDao.saveCache(characters)


            MediatorResult.Success(
                endOfPaginationReached = true
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getCharactersListByIdByRemote(
        characterListFilter: List<Int>
    ): List<CharacterForDetailCacheEntity> {
        return characterListApi.getCharacterListByIdList(characterListFilter.toString()).body()!!
            .toCharacterCacheListEntity()
    }
}