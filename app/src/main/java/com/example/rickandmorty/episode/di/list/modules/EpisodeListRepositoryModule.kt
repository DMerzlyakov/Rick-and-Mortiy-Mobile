package com.example.rickandmorty.episode.di.list.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.episode.data.list.EpisodeListRepositoryImpl
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.local.EpisodesDatabase
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi
import com.example.rickandmorty.episode.domain.list.EpisodeListRepository
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
class EpisodeListRepositoryModule {


    @Singleton
    @Provides
    fun provideEpisodeListRepository(episodeListRepositoryImpl: EpisodeListRepositoryImpl): EpisodeListRepository {
        return episodeListRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideEpisodesDatabase(context: Context): EpisodesDatabase {
        return Room.databaseBuilder(
            context,
            EpisodesDatabase::class.java,
            "episodes"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEpisodeListApi(retrofit: Retrofit): EpisodeListApi {
        return retrofit.create(EpisodeListApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEpisodeDao(database: EpisodesDatabase): EpisodeDao {
        return database.episodeDao()
    }


    @Singleton
    @Provides
    fun provideEpisodeListRepositoryImpl(episodeListApi: EpisodeListApi, episodeDao: EpisodeDao): EpisodeListRepositoryImpl {
        return EpisodeListRepositoryImpl(
            episodeListApi,
            episodeDao
        )
    }

}