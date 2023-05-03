package com.example.rickandmorty.character.data.list.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity

@Database(entities = [(CharacterEntity::class), (CharacterForDetailCacheEntity::class)], version = 7)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao():CharacterListDao
}