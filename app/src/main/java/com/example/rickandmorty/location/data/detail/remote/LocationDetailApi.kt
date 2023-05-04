package com.example.rickandmorty.location.data.detail.remote

import com.example.rickandmorty.location.data.detail.remote.model.LocationDetailDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationDetailApi {

    @GET("/api/location/{id}")
    fun getDetailCharacter(@Path("id") id: Int): Single<LocationDetailDto>
}