package com.example.rickandmorty.location.data.list.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.location.data.list.local.model.LocationEntity

@Database(entities = [(LocationEntity::class)], version = 1)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun locationDao():LocationDao
}