package com.example.rickandmorty.character.data.list.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity

@Database(entities = [(CharacterEntity::class)], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao():CharactersListDao
}