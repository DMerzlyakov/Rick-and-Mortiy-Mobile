package com.example.rickandmorty.character.data.detail.remote

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailApi {

    @GET("/api/character/{id}")
    fun getDetailCharacter(@Path("id") id: Int): Single<CharacterDetailDTO>
}