package com.example.rickandmorty.character.di.modules.list

import com.example.rickandmorty.character.domain.list.GetCharacterListByIdUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListByIdUseCaseImpl
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCase
import com.example.rickandmorty.character.domain.list.GetCharacterListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface CharactersListUseCaseModule {

    @Binds
    fun bindGetCharacterListUseCase(getCharacterListUseCaseImpl: GetCharacterListUseCaseImpl): GetCharacterListUseCase

    @Binds
    fun bindGetCharacterListByIdUseCase(getCharacterListByIdUseCaseImpl: GetCharacterListByIdUseCaseImpl): GetCharacterListByIdUseCase

}