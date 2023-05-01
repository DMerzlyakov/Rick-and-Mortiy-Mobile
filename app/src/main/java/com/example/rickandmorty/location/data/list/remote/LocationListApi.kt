package com.example.rickandmorty.location.data.list.remote

import com.example.rickandmorty.location.data.list.remote.model.LocationDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationListApi {

    @GET("api/location/")
    suspend fun getAllLocation(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String,
    ): Response<LocationDTO>

//
//    @GET("/api/character/{id}")
//    suspend fun getCharacterDetail(@Path("id") id: Int) : Response<CharacterDetailDTO>
//
//
//    @GET("/api/character/{idList}")
//    suspend fun getCharacterListByIdList(@Path("idList") idList: String) : Response<CharacterDetailDTO>
}