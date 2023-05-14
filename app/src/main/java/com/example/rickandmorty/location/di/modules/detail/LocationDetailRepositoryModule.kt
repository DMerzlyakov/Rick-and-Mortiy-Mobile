package com.example.rickandmorty.location.di.modules.detail

import com.example.rickandmorty.location.data.detail.LocationDetailRepositoryImpl
import com.example.rickandmorty.location.data.detail.mapper.LocationDetailDtoToLocationEntityMapper
import com.example.rickandmorty.location.data.detail.mapper.LocationEntityToLocationDetailDomainMapper
import com.example.rickandmorty.location.data.detail.remote.LocationDetailApi
import com.example.rickandmorty.location.data.list.local.LocationDao
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
    fun provideLocationListApi(retrofit: Retrofit): LocationDetailApi {
        return retrofit.create(LocationDetailApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLocationDetailRepositoryImpl(
        locationDetailApi: LocationDetailApi,
        locationDao: LocationDao,
        dtoToEntityMapper: LocationDetailDtoToLocationEntityMapper,
        entityToDomainMapper: LocationEntityToLocationDetailDomainMapper
    ): LocationDetailRepositoryImpl {
        return LocationDetailRepositoryImpl(
            locationDetailApi, locationDao,
            dtoToEntityMapper, entityToDomainMapper
        )
    }

}