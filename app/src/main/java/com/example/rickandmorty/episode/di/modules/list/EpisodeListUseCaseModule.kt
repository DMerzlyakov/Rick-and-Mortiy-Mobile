package com.example.rickandmorty.episode.di.modules.list

import com.example.rickandmorty.episode.domain.list.GetEpisodeListByIdUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListByIdUseCaseImpl
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface EpisodeListUseCaseModule {

    @Binds
    fun bindGetEpisodeListUseCase(getEpisodeListUseCaseImpl: GetEpisodeListUseCaseImpl): GetEpisodeListUseCase

    @Binds
    fun bindGetEpisodeListByIdUseCase(getEpisodeListByIdUseCaseImpl: GetEpisodeListByIdUseCaseImpl): GetEpisodeListByIdUseCase

}