package com.example.rickandmorty.character.data.list.remote

import com.example.rickandmorty.character.data.list.remote.model.CharacterPagedDTO
import com.example.rickandmorty.character.data.list.remote.model.CharacterResultsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterListApi {

    @GET("api/character/")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("gender") gender: String,
    ): Response<CharacterPagedDTO>


    @GET("/api/character/{idList}")
    suspend fun getCharacterListByIdList(@Path("idList") idList: String): Response<List<CharacterResultsDTO>>
}