package com.example.rickandmorty.data.remote.dto

import com.example.rickandmorty.domain.Character
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    var origin: Origin,
    @SerializedName("location")
    var location: Location,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    var episode: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
) {
    fun toCharacter() : Character {
        return Character(id, name, status, species, gender, image)
    }
}