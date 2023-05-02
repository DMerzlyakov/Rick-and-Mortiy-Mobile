package com.example.rickandmorty.location.data.detail.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.location.data.detail.local.model.LocationEntity

@Database(entities = [(LocationEntity::class)], version = 2)
abstract class LocationDatabase : RoomDatabase() {

    abstract fun locationDao():LocationDetailDao
}