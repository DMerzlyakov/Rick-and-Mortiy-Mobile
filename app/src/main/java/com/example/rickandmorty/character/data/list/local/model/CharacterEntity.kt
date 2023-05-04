package com.example.rickandmorty.character.data.list.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.utils.ListIntConverter

@Entity(tableName = "characters")
@TypeConverters(ListIntConverter::class)
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlAvatar: String,
    val originId: Int?,
    val originName: String,
    val locationId: Int?,
    val locationName: String,
    val episodes: List<Int>
)
