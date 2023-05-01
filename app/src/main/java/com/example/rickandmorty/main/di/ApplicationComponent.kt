package com.example.rickandmorty.main.di

import android.content.Context
import com.example.rickandmorty.character.di.list.CharacterListRepositoryModule
import com.example.rickandmorty.character.di.list.CharactersListUseCaseModule
import com.example.rickandmorty.character.presentation.detail.CharacterDetailsFragment
import com.example.rickandmorty.character.presentation.list.CharactersListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CharacterListRepositoryModule::class,
        CharactersListUseCaseModule::class
    ]
)
interface ApplicationComponent {


    fun inject(fragment: CharactersListFragment)

    fun inject(fragment: CharacterDetailsFragment)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}