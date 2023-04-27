package com.example.rickandmorty.character.data.list

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.character.data.list.mapper.CharacterDtoToCharacterListMapper
import com.example.rickandmorty.character.data.list.pagingSource.CharacterPagingSource
import com.example.rickandmorty.character.data.list.pagingSource.CharactersPageLoader
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.domain.list.CharacterListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CharacterListRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val characterListApi: CharacterListApi
) : CharacterListRepository {

    private val characterDtoToCharacterListMapper = CharacterDtoToCharacterListMapper()
    override suspend fun getPagedCharacters(
        name: String, status: String,
        species: String, gender: String
    ): Flow<PagingData<CharacterDomain>> {
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
            pagingSourceFactory = {
                CharacterPagingSource(
                    loader,
                    PAGE_SIZE
                )
            }
        ).flow
    }

    private suspend fun getUsers(
        pageIndex: Int, name: String = "", status: String = "",
        species: String = "", gender: String = ""
    ): List<CharacterDomain> =
        withContext(ioDispatcher) {
            Log.d("NETWORK", "Load character page $pageIndex - $name $status $species $gender")
            // get page
            val list = characterListApi.getAllCharacters(pageIndex, name, status, species, gender).body()

            // map UserDbEntity to User
            return@withContext characterDtoToCharacterListMapper(list!!)
        }

    private companion object {
        const val PAGE_SIZE = 20
    }
}