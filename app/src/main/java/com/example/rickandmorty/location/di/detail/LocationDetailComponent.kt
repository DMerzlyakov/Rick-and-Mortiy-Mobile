package com.example.rickandmorty.location.di.detail

import com.example.rickandmorty.location.di.detail.bridge.LocationDetailDep
import com.example.rickandmorty.location.di.detail.modules.LocationDetailRepositoryModule
import com.example.rickandmorty.location.di.detail.modules.LocationDetailUseCaseModule
import com.example.rickandmorty.location.domain.detail.LocationDetailFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        LocationDetailRepositoryModule::class,
        LocationDetailUseCaseModule::class
    ],
    dependencies = [LocationDetailDep::class]
)
interface LocationDetailComponent {

    fun inject(fragment: LocationDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            locationDetailDep: LocationDetailDep
        ): LocationDetailComponent
    }
}