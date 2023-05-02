package com.example.rickandmorty.episode.di.list

import com.example.rickandmorty.episode.di.list.bridge.EpisodeListDep
import com.example.rickandmorty.episode.di.list.modules.EpisodeListRepositoryModule
import com.example.rickandmorty.episode.di.list.modules.EpisodeListUseCaseModule
import com.example.rickandmorty.episode.presentation.list.EpisodeListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        EpisodeListRepositoryModule::class,
        EpisodeListUseCaseModule::class
    ],
    dependencies = [EpisodeListDep::class]
)
interface EpisodeListComponent {

    fun inject(fragment: EpisodeListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            episodeListDep: EpisodeListDep
        ): EpisodeListComponent
    }
}