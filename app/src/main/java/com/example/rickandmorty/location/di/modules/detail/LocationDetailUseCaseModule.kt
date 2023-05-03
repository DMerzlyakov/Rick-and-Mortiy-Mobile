package com.example.rickandmorty.location.di.modules.detail

import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCase
import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCaseImpl
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import com.example.rickandmorty.location.domain.list.GetLocationListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface LocationDetailUseCaseModule {

    @Binds
    fun bindGetLocationDetailUseCase(getLocationDetailUseCaseImpl: GetLocationDetailUseCaseImpl): GetLocationDetailUseCase

}