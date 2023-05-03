package com.example.rickandmorty.episode.data.list.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity

@Database(entities = [(EpisodeEntity::class), (EpisodeForDetailCacheEntity::class)], version = 3)
abstract class EpisodesDatabase : RoomDatabase() {

    abstract fun episodeDao():EpisodeDao
}