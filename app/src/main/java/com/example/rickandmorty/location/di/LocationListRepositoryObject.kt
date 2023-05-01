package com.example.rickandmorty.location.di

import android.content.Context
import com.example.rickandmorty.location.data.list.LocationsListRepositoryImpl
import com.example.rickandmorty.location.data.list.remote.LocationListApi
import com.example.rickandmorty.location.domain.list.LocationsListRepository
import kotlinx.coroutines.Dispatchers

object LocationListRepositoryObject {

    private var usersRepository: LocationsListRepository? = null

    fun getLocationRepository(context: Context): LocationsListRepository {
        if (usersRepository == null){
            usersRepository = create(context)
        }

        return usersRepository!!
    }


    private fun create(context: Context): LocationsListRepositoryImpl {
        return LocationsListRepositoryImpl(
            Dispatchers.IO,
            RetrofitClient.getClient().create(LocationListApi::class.java),
            LocationDatabase.getDatabase(context).locationDao()
        )
    }
}