package com.example.rickandmorty.character.di.modules.detail

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase
import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface CharacterDetailUseCaseModule {

    @Binds
    fun bindGetCharacterDetailUseCase(getCharacterDetailUseCaseImpl: GetCharacterDetailUseCaseImpl): GetCharacterDetailUseCase

}