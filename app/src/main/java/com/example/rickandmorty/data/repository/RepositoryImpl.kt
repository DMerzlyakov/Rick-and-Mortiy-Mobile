package com.example.rickandmorty.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.pagingSource.CharacterPagingSource
import com.example.rickandmorty.data.pagingSource.CharactersPageLoader
import com.example.rickandmorty.data.remote.CharacterApi
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.CharacterDetail
import com.example.rickandmorty.domain.RepositoryInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val characterApi: CharacterApi
) : RepositoryInterface {

    override suspend fun getPagedCharacters(
        name: String, status: String,
        species: String, gender: String
    ): Flow<PagingData<Character>> {
        val loader: CharactersPageLoader = { pageIndex, _ ->
            getUsers(
                pageIndex, name, status,
                species, gender
            )
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE

            ),
            pagingSourceFactory = { CharacterPagingSource(loader, PAGE_SIZE) }
        ).flow
    }

    override suspend fun getCharacterById(mId: Int): Flow<Result<CharacterDetail>> =
        flow {
            val character = characterApi.getCharacterDetail(mId).body()
            if (character != null) {
                emit(Result.success(character.toCharacterDetail()))
            } else {
                throw RuntimeException("Response not correct")
            }
        }.catch { e -> emit(Result.failure(e))}
            .flowOn(Dispatchers.IO)

    private suspend fun getUsers(
        pageIndex: Int, name: String = "", status: String = "",
        species: String = "", gender: String = ""
    ): List<Character> =
        withContext(ioDispatcher) {
            Log.d("NETWORK", "Load character page $pageIndex - $name $status $species $gender")
            // get page
            val list = characterApi.getCharacters(pageIndex, name, status, species, gender).body()
                ?.getListCharacter()

            // map UserDbEntity to User
            return@withContext list!!
        }

    private companion object {
        const val PAGE_SIZE = 20
    }
}