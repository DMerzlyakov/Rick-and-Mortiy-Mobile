package com.example.rickandmorty.main.di

import android.content.Context
import com.example.rickandmorty.character.di.detail.bridge.CharacterDetailDep
import com.example.rickandmorty.character.di.list.bridge.CharactersDep
import com.example.rickandmorty.episode.di.list.bridge.EpisodeListDep
import com.example.rickandmorty.location.di.detail.bridge.LocationDetailDep
import com.example.rickandmorty.location.di.list.bridge.LocationListDep
import com.example.rickandmorty.main.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [NetworkModule::class])
interface ApplicationComponent : CharactersDep, CharacterDetailDep, LocationListDep,
    LocationDetailDep, EpisodeListDep {

    override val context: Context

    override val retrofit: Retrofit

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}