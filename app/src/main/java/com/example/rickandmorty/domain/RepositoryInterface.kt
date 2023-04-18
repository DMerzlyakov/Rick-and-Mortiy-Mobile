package com.example.rickandmorty.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.rickandmorty.data.remote.dto.CharacterDetailDTO
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {

    suspend fun getPagedCharacters(
        name: String = "", status: String = "",
        species: String = "", gender: String = ""
    ): Flow<PagingData<Character>>

    suspend fun getCharacterById(mId: Int): Flow<Result<CharacterDetail>>
}