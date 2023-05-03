package com.example.rickandmorty.episode.data.list.remote

import com.example.rickandmorty.episode.data.list.remote.model.EpisodeDto
import com.example.rickandmorty.episode.data.list.remote.model.EpisodeResultsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeListApi {

    @GET("api/episode/")
    suspend fun getAllEpisode(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String,
    ): Response<EpisodeDto>

    @GET("api/episode/{idList}")
    suspend fun getEpisodeListByIdList(@Path("idList") idList: String): Response<List<EpisodeResultsDTO>>

}