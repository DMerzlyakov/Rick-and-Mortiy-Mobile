package com.example.rickandmorty.episode.di.modules.detail

import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCase
import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface EpisodeDetailUseCaseModule {

    @Binds
    fun bindGetEpisodeDetailUseCase(getEpisodeDetailUseCaseImpl: GetEpisodeDetailUseCaseImpl): GetEpisodeDetailUseCase

}