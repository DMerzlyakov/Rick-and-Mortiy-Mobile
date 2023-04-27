package com.example.rickandmorty.character.data.list.remote.dto

import com.google.gson.annotations.SerializedName

data class CharactersDTO(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var results: List<Results>
    )



