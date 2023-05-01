package com.example.rickandmorty.location.di.list.modules

import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import com.example.rickandmorty.location.domain.list.GetLocationListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface LocationListUseCaseModule {

    @Binds
    fun bindGetLocationListUseCase(getLocationListUseCaseImpl: GetLocationListUseCaseImpl): GetLocationListUseCase

}