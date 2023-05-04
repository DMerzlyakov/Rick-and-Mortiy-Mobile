package com.example.rickandmorty.episode.data.list.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_cache")
data class EpisodeForDetailCacheEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
)
