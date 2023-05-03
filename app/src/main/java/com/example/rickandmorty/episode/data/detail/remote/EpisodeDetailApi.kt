package com.example.rickandmorty.episode.data.detail.remote

import com.example.rickandmorty.episode.data.detail.remote.model.EpisodeDetailDTO
import com.example.rickandmorty.location.data.detail.remote.model.LocationDetailDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeDetailApi {

    @GET("/api/location/{id}")
    fun getEpisodeDetail(@Path("id") id: Int): Single<EpisodeDetailDTO>
}