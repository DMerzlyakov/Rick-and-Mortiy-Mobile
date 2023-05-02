package com.example.rickandmorty.location.data.detail.remote

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.location.data.detail.remote.model.LocationDetailDto
import com.example.rickandmorty.location.data.list.remote.model.LocationDTO
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationDetailApi {

    @GET("/api/location/{id}")
    fun getDetailCharacter(@Path("id") id: Int): Observable<LocationDetailDto>
}