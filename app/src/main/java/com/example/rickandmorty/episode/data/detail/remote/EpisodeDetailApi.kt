package com.example.rickandmorty.episode.data.detail.remote

import com.example.rickandmorty.episode.data.detail.remote.model.EpisodeDetailDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeDetailApi {

    @GET("/api/episode/{id}")
    fun getEpisodeDetail(@Path("id") id: Int): Single<EpisodeDetailDTO>
}