package com.example.rickandmorty.character.data.list.remote.dto

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    var count: Int,
    @SerializedName("pages")
    var pages: Int,
    @SerializedName("next")
    val next: String
)