package com.example.rickandmorty.episode.di.modules.detail

import com.example.rickandmorty.episode.data.detail.EpisodeDetailRepositoryImpl
import com.example.rickandmorty.episode.data.detail.mapper.EpisodeDetailDtoToEpisodeEntityMapper
import com.example.rickandmorty.episode.data.detail.mapper.EpisodeEntityToEpisodeDetailDomain
import com.example.rickandmorty.episode.data.detail.remote.EpisodeDetailApi
import com.example.rickandmorty.episode.data.list.local.EpisodeDao
import com.example.rickandmorty.episode.domain.detail.EpisodeDetailRepository
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
    fun provideEpisodeListRepositoryImpl(
        episodeDetailApi: EpisodeDetailApi,
        episodeDao: EpisodeDao,
        dtoToEntityMapper: EpisodeDetailDtoToEpisodeEntityMapper,
        entityToDomainMapper: EpisodeEntityToEpisodeDetailDomain
    ): EpisodeDetailRepositoryImpl {
        return EpisodeDetailRepositoryImpl(
            episodeDetailApi, episodeDao,
            dtoToEntityMapper, entityToDomainMapper
        )
    }

}