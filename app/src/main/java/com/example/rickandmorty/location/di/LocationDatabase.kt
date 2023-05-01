package com.example.rickandmorty.location.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.location.data.list.local.LocationDatabase

object LocationDatabase {


    var database: LocationDatabase? = null

    fun getDatabase(context: Context): LocationDatabase {

        if (database == null) {
            database = create(context)
        }

        return database!!
    }

    private fun create(context: Context): LocationDatabase =
        Room.databaseBuilder(
            context,
            LocationDatabase::class.java,
            "db"
        ).fallbackToDestructiveMigration()
            .build()


}