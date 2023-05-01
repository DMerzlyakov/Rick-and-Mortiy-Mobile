package com.example.rickandmorty.location.di.list.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.location.data.list.LocationsListRepositoryImpl
import com.example.rickandmorty.location.data.list.local.LocationDao
import com.example.rickandmorty.location.data.list.local.LocationDatabase
import com.example.rickandmorty.location.data.list.remote.LocationListApi
import com.example.rickandmorty.location.domain.list.LocationsListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class LocationListRepositoryModule {


    @Singleton
    @Provides
    fun provideLocationsListRepository(locationsListRepositoryImpl: LocationsListRepositoryImpl): LocationsListRepository {
        return locationsListRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideCharacterDatabase(context: Context): LocationDatabase {
        return Room.databaseBuilder(
            context,
            LocationDatabase::class.java,
            "location"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideLocationListApi(retrofit: Retrofit): LocationListApi {
        return retrofit.create(LocationListApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: LocationDatabase): LocationDao {
        return database.locationDao()
    }


    @Singleton
    @Provides
    fun provideCharacterListRepositoryImpl(locationListApi: LocationListApi, locationDao: LocationDao): LocationsListRepositoryImpl {
        return LocationsListRepositoryImpl(
            locationListApi,
            locationDao
        )
    }

}