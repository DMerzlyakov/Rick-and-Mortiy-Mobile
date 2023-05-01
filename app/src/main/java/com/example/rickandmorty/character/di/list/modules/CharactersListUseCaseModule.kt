package com.example.rickandmorty.character.di.list.modules

import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface CharactersListUseCaseModule {

    @Binds
    fun bindGetCharacterListUseCase(getCharacterListUseCaseImpl: GetCharacterListUseCaseImpl): GetCharacterListUseCase

}