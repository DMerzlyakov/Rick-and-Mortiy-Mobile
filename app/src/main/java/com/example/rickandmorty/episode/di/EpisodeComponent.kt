package com.example.rickandmorty.episode.di

import com.example.rickandmorty.episode.di.bridge.EpisodeDep
import com.example.rickandmorty.episode.di.modules.detail.EpisodeDetailRepositoryModule
import com.example.rickandmorty.episode.di.modules.detail.EpisodeDetailUseCaseModule
import com.example.rickandmorty.episode.di.modules.list.EpisodeListRepositoryModule
import com.example.rickandmorty.episode.di.modules.list.EpisodeListUseCaseModule
import com.example.rickandmorty.episode.presentation.detail.EpisodeDetailsFragment
import com.example.rickandmorty.episode.presentation.list.EpisodeListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        EpisodeListRepositoryModule::class,
        EpisodeListUseCaseModule::class,
        EpisodeDetailRepositoryModule::class,
        EpisodeDetailUseCaseModule::class
    ],
    dependencies = [EpisodeDep::class]
)
interface EpisodeComponent {

    fun inject(fragment: EpisodeListFragment)

    fun inject(fragment: EpisodeDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            episodeDep: EpisodeDep
        ): EpisodeComponent
    }
}