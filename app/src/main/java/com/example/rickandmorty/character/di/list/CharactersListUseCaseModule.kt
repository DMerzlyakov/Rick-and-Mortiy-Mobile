package com.example.rickandmorty.character.di.list

import com.example.rickandmorty.character.domain.list.CharacterListRepository
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface CharactersListUseCaseModule {

    @Binds
    fun bindGetCharacterListUseCase(getCharacterListUseCaseImpl: GetCharacterListUseCaseImpl): GetCharacterListUseCase

}