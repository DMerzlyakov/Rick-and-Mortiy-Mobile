package com.example.rickandmorty.location.di

import com.example.rickandmorty.location.di.modules.detail.LocationDetailRepositoryModule
import com.example.rickandmorty.location.di.modules.detail.LocationDetailUseCaseModule
import com.example.rickandmorty.location.di.bridge.LocationDep
import com.example.rickandmorty.location.di.modules.list.LocationListRepositoryModule
import com.example.rickandmorty.location.di.modules.list.LocationListUseCaseModule
import com.example.rickandmorty.location.presentation.detail.LocationDetailFragment
import com.example.rickandmorty.location.presentation.list.LocationListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        LocationListRepositoryModule::class,
        LocationListUseCaseModule::class,
        LocationDetailRepositoryModule::class,
        LocationDetailUseCaseModule::class
    ],
    dependencies = [LocationDep::class]
)
interface LocationComponent {

    fun inject(fragment: LocationListFragment)

    fun inject(fragment: LocationDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            LocationDep: LocationDep
        ): LocationComponent
    }
}