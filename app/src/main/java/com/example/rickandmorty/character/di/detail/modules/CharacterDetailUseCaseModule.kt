package com.example.rickandmorty.character.di.detail.modules

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase
import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface CharacterDetailUseCaseModule {

    @Binds
    fun bindGetCharacterDetailUseCase(getCharacterDetailUseCaseImpl: GetCharacterDetailUseCaseImpl): GetCharacterDetailUseCase

}