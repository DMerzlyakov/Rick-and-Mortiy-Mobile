package com.example.rickandmorty.character.di.list

import com.example.rickandmorty.character.di.detail.modules.CharacterDetailRepositoryModule
import com.example.rickandmorty.character.di.list.bridge.CharactersDep
import com.example.rickandmorty.character.di.list.modules.CharacterListRepositoryModule
import com.example.rickandmorty.character.di.list.modules.CharactersListUseCaseModule
import com.example.rickandmorty.character.presentation.list.CharactersListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CharacterListRepositoryModule::class,
        CharactersListUseCaseModule::class,
        CharacterDetailRepositoryModule::class
    ],
    dependencies = [CharactersDep::class]
)
interface CharacterListComponent {

    fun inject(fragment: CharactersListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            charactersDep: CharactersDep
        ): CharacterListComponent
    }
}