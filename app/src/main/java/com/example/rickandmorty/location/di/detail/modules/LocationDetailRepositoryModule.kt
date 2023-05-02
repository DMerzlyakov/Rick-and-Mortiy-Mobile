package com.example.rickandmorty.location.di.detail.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.location.data.detail.LocationDetailRepositoryImpl
import com.example.rickandmorty.location.data.detail.local.LocationDetailDao
import com.example.rickandmorty.location.data.detail.local.LocationDatabase
import com.example.rickandmorty.location.data.detail.remote.LocationDetailApi
import com.example.rickandmorty.location.domain.detail.LocationDetailRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class LocationDetailRepositoryModule {


    @Singleton
    @Provides
    fun provideLocationDetailRepository(locationDetailRepositoryImpl: LocationDetailRepositoryImpl): LocationDetailRepository {
        return locationDetailRepositoryImpl
    }


    @Singleton
    @Provides
    fun provideLocationDatabase(context: Context): LocationDatabase {
        return Room.databaseBuilder(
            context,
            LocationDatabase::class.java,
            "location"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideLocationListApi(retrofit: Retrofit): LocationDetailApi {
        return retrofit.create(LocationDetailApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationDao(database: LocationDatabase): LocationDetailDao {
        return database.locationDao()
    }


    @Singleton
    @Provides
    fun provideLocationDetailRepositoryImpl(locationDetailApi: LocationDetailApi, locationDetailDao: LocationDetailDao): LocationDetailRepositoryImpl {
        return LocationDetailRepositoryImpl(
            locationDetailApi,
            locationDetailDao
        )
    }

}