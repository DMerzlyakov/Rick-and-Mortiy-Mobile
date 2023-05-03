package com.example.rickandmorty.episode.data.list.remote.model

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var results: List<EpisodeResultsDTO>
)
