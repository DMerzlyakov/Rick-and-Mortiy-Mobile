package com.example.rickandmorty.character.di

import com.example.rickandmorty.character.di.modules.detail.CharacterDetailRepositoryModule
import com.example.rickandmorty.character.di.modules.detail.CharacterDetailUseCaseModule
import com.example.rickandmorty.character.di.bridge.CharactersDep
import com.example.rickandmorty.character.di.modules.list.CharacterListRepositoryModule
import com.example.rickandmorty.character.di.modules.list.CharactersListUseCaseModule
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import com.example.rickandmorty.character.presentation.list.CharacterListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CharacterListRepositoryModule::class,
        CharactersListUseCaseModule::class,
        CharacterDetailRepositoryModule::class,
        CharacterDetailUseCaseModule::class
    ],
    dependencies = [CharactersDep::class]
)
interface CharacterComponent {

    fun inject(fragment: CharacterListFragment)

    fun inject(fragment: CharacterDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            charactersDep: CharactersDep
        ): CharacterComponent
    }
}