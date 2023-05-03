package com.example.rickandmorty.character.data.list.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_cache")
data class CharacterForDetailCacheEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlAvatar: String,
)
