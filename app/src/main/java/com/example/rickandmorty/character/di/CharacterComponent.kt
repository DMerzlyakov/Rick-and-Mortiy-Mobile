package com.example.rickandmorty.character.di

import com.example.rickandmorty.character.di.bridge.CharactersDep
import com.example.rickandmorty.character.di.modules.detail.CharacterDetailRepositoryModule
import com.example.rickandmorty.character.di.modules.detail.CharacterDetailUseCaseModule
import com.example.rickandmorty.character.di.modules.list.CharacterListRepositoryModule
import com.example.rickandmorty.character.di.modules.list.CharactersListUseCaseModule
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import com.example.rickandmorty.character.presentation.list.CharacterListFragment
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        CharacterListRepositoryModule::class,
        CharactersListUseCaseModule::class,
        CharacterDetailRepositoryModule::class,
        CharacterDetailUseCaseModule::class
    ],
    dependencies = [CharactersDep::class]
)
@Singleton
interface CharacterComponent {

    @Singleton
    fun inject(fragment: CharacterListFragment)

    @Singleton
    fun inject(fragment: CharacterDetailsFragment)

    @Singleton
    @Component.Factory
    interface Factory {
        fun create(
            charactersDep: CharactersDep
        ): CharacterComponent
    }
}