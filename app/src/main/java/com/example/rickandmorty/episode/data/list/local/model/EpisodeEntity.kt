package com.example.rickandmorty.episode.data.list.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.utils.ListIntConverter

@Entity(tableName = "episodes")
@TypeConverters(ListIntConverter::class)
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<Int>
)
