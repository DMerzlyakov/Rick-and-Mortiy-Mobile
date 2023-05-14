package com.example.rickandmorty.episode.di.modules.list

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.episode.data.list.EpisodeListRepositoryImpl
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.local.EpisodesDatabase
import com.example.rickandmorty.episode.data.list.mapper.EpisodeCacheEntityToEpisodeDomainMapper
import com.example.rickandmorty.episode.data.list.mapper.EpisodeDtoToEpisodeEntityMapper
import com.example.rickandmorty.episode.data.list.mapper.EpisodeEntityToEpisodeDomainMapper
import com.example.rickandmorty.episode.data.list.mapper.EpisodeResultDtoToEpisodeCacheEntityMapper
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi
import com.example.rickandmorty.episode.domain.list.EpisodeListRepository
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
    fun provideEpisodeListRepositoryImpl(
        episodeListApi: EpisodeListApi, episodeDao: EpisodeDao,
        entityCacheToDomainPagingMapper: EpisodeCacheEntityToEpisodeDomainMapper,
        entityToDomainPagingMapper: EpisodeEntityToEpisodeDomainMapper,
        dtoToEntityMapper: EpisodeDtoToEpisodeEntityMapper,
        dtoToCacheEntityMapper: EpisodeResultDtoToEpisodeCacheEntityMapper
    ): EpisodeListRepositoryImpl {
        return EpisodeListRepositoryImpl(
            episodeListApi, episodeDao,
            entityCacheToDomainPagingMapper,
            entityToDomainPagingMapper,
            dtoToEntityMapper,
            dtoToCacheEntityMapper
        )
    }

}