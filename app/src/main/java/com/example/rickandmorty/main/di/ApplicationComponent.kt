package com.example.rickandmorty.main.di

import android.content.Context
import com.example.rickandmorty.character.di.bridge.CharactersDep
import com.example.rickandmorty.episode.di.bridge.EpisodeDep
import com.example.rickandmorty.location.di.bridge.LocationDep
import com.example.rickandmorty.main.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [NetworkModule::class])
interface ApplicationComponent : CharactersDep, LocationDep,
    EpisodeDep {

    override val context: Context

    override val retrofit: Retrofit

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}