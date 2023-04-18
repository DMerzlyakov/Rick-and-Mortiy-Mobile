package com.example.rickandmorty.data.remote.dto

import com.example.rickandmorty.domain.Character
import com.google.gson.annotations.SerializedName

data class CharactersDTO(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    var results: List<Results>
    ) {
    fun getListCharacter(): List<Character> {
        return results.map { it.toCharacter() }
    }
}




