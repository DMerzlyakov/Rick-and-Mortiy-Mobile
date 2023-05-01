package com.example.rickandmorty.character.data.list.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.rickandmorty.character.data.list.local.CharactersListDao
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.mapper.toCharacterEntity
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import kotlin.math.log

@OptIn(ExperimentalPagingApi::class)
class CharactersListRemoteMediator(
    private val characterApi: CharacterListApi,
    private val charactersListDao: CharactersListDao,
    private val name: String,
    private val status: String,
    private val species: String,
    private val gender: String
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

            val characters = getCharactersByRemote(name, status, species, gender)
            Log.e("RESPONCE", characters.toString())
            if (loadType == LoadType.REFRESH){
                charactersListDao.refresh(characters, name, status, species, gender)
            } else{
                charactersListDao.save(characters)
            }
            MediatorResult.Success(
                endOfPaginationReached = characters.size < limit
            )
        } catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getCharactersByRemote(
        name: String,
        status: String,
        species: String,
        gender: String
    ): List<CharacterEntity> {
        return characterApi.getAllCharacters(pageIndex, name, status, species, gender).body()!!.toCharacterEntity()
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