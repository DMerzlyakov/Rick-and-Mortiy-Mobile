package com.example.rickandmorty.location.data.detail.remote.model

import com.google.gson.annotations.SerializedName

data class LocationDetailDto(

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("residents")
    var residents: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
)
