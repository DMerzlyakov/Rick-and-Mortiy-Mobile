package com.example.rickandmorty.episode.di.modules.detail

import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCase
import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCaseImpl
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCaseImpl
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import com.example.rickandmorty.location.domain.list.GetLocationListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface EpisodeDetailUseCaseModule {

    @Binds
    fun bindGetEpisodeDetailUseCase(getEpisodeDetailUseCaseImpl: GetEpisodeDetailUseCaseImpl): GetEpisodeDetailUseCase

}