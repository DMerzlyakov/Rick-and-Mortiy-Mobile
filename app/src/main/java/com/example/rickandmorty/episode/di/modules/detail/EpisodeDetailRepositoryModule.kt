package com.example.rickandmorty.episode.di.modules.detail

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.episode.data.detail.EpisodeDetailRepositoryImpl
import com.example.rickandmorty.episode.data.detail.remote.EpisodeDetailApi
import com.example.rickandmorty.episode.data.list.EpisodeListRepositoryImpl
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.data.list.local.EpisodesDatabase
import com.example.rickandmorty.episode.data.list.remote.EpisodeListApi
import com.example.rickandmorty.episode.domain.detail.EpisodeDetailRepository
import com.example.rickandmorty.episode.domain.list.EpisodeListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class EpisodeDetailRepositoryModule {


    @Singleton
    @Provides
    fun provideEpisodeDetailRepository(episodeDetailRepositoryImpl: EpisodeDetailRepositoryImpl): EpisodeDetailRepository {
        return episodeDetailRepositoryImpl
    }


    @Singleton
    @Provides
    fun provideEpisodeDetailApi(retrofit: Retrofit): EpisodeDetailApi {
        return retrofit.create(EpisodeDetailApi::class.java)
    }



    @Singleton
    @Provides
    fun provideEpisodeListRepositoryImpl(episodeDetailApi: EpisodeDetailApi, episodeDao: EpisodeDao): EpisodeDetailRepositoryImpl {
        return EpisodeDetailRepositoryImpl(
            episodeDetailApi,
            episodeDao
        )
    }

}