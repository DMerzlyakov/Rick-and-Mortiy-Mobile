package com.example.rickandmorty.character.di.detail

import com.example.rickandmorty.character.di.detail.bridge.CharacterDetailDep
import com.example.rickandmorty.character.di.detail.modules.CharacterDetailRepositoryModule
import com.example.rickandmorty.character.di.detail.modules.CharacterDetailUseCaseModule
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CharacterDetailRepositoryModule::class,
        CharacterDetailUseCaseModule::class
    ],
    dependencies = [CharacterDetailDep::class]
)
interface CharacterDetailComponent {

    fun inject(fragment: CharacterDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            characterDetailDep: CharacterDetailDep
        ): CharacterDetailComponent
    }
}