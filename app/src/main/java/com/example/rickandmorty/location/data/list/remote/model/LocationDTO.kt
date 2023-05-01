package com.example.rickandmorty.location.data.list.remote.model

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var results: List<Results>
)
