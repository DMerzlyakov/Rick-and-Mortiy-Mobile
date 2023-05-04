package com.example.rickandmorty.episode.data.detail.remote.model

import com.google.gson.annotations.SerializedName

data class EpisodeDetailDTO(

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("characters")
    var characters: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
)