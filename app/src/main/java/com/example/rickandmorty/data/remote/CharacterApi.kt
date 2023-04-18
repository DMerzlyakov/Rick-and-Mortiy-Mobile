package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.dto.CharacterDetailDTO
import com.example.rickandmorty.data.remote.dto.CharactersDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.rickandmorty.domain.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Path

interface CharacterApi {

    @GET("api/character/")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("gender") gender: String,
    ): Response<CharactersDTO>


    @GET("/api/character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int) : Response<CharacterDetailDTO>
}