package com.example.rickandmorty.episode.data.list.remote

import com.example.rickandmorty.episode.data.list.remote.model.EpisodeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeListApi {

    @GET("api/episode/")
    suspend fun getAllEpisode(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String,
    ): Response<EpisodeDto>

}