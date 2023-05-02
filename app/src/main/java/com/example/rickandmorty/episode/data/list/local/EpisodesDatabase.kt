package com.example.rickandmorty.episode.data.list.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity

@Database(entities = [(EpisodeEntity::class)], version = 1)
abstract class EpisodesDatabase : RoomDatabase() {

    abstract fun episodeDao():EpisodeDao
}