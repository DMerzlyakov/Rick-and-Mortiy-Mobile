package com.example.rickandmorty.character.data.list.remote.dto

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) {
    fun getId() = url.split("/").last().toIntOrNull()
}