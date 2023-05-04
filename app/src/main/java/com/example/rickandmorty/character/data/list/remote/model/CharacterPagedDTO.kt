package com.example.rickandmorty.character.data.list.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterPagedDTO(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var results: List<CharacterResultsDTO>
)



