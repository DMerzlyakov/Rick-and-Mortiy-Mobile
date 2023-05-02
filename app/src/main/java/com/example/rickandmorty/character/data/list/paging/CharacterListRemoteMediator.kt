package com.example.rickandmorty.character.data.list.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.mapper.toCharacterEntity
import com.example.rickandmorty.character.data.list.mapper.toCharacterListEntity
import com.example.rickandmorty.character.data.list.remote.CharacterListApi

@OptIn(ExperimentalPagingApi::class)
class CharacterListRemoteMediator(
    private val characterApi: CharacterListApi,
    private val characterListDao: CharacterListDao,
    private val name: String,
    private val status: String,
    private val species: String,
    private val gender: String,
    private val characterListFilter: List<Int>
) : RemoteMediator<Int, CharacterEntity>() {

    private var pageIndex = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {

        pageIndex =
            getPagedIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize

        return try {

            val characters =
                if (characterListFilter.isEmpty()) {
                    getCharactersByRemote(name, status, species, gender)
                } else {
                    getCharactersListByIdByRemote(characterListFilter)
                }

            Log.e("MEDIATOR", characters.toString())

            if (loadType == LoadType.REFRESH && characterListFilter.isEmpty()) {
                characterListDao.refresh(characters, name, status, species, gender)
            } else {
                characterListDao.save(characters)
            }

            val endOfPaginationStatus = if(
                characterListFilter.isEmpty()
            ) {
                characters.size < limit
            } else{
                true
            }


            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationStatus

            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getCharactersByRemote(
        name: String,
        status: String,
        species: String,
        gender: String
    ): List<CharacterEntity> {
        return characterApi.getAllCharacters(pageIndex, name, status, species, gender).body()!!
            .toCharacterEntity()
    }

    private suspend fun getCharactersListByIdByRemote(
        characterListFilter: List<Int>
    ): List<CharacterEntity> {
        return characterApi.getCharacterListByIdList(characterListFilter.toString()).body()!!
            .toCharacterListEntity()
    }

    private fun getPagedIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }

        return pageIndex

    }
}