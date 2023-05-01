package com.example.rickandmorty.location.di.list

import com.example.rickandmorty.location.di.list.bridge.LocationListDep
import com.example.rickandmorty.location.di.list.modules.LocationListRepositoryModule
import com.example.rickandmorty.location.di.list.modules.LocationListUseCaseModule
import com.example.rickandmorty.location.presentation.list.LocationsListFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        LocationListRepositoryModule::class,
        LocationListUseCaseModule::class
    ],
    dependencies = [LocationListDep::class]
)
interface LocationListComponent {

    fun inject(fragment: LocationsListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            LocationListDep: LocationListDep
        ): LocationListComponent
    }
}