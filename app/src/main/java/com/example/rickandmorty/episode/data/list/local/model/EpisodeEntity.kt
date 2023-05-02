package com.example.rickandmorty.episode.data.list.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String
)
