package com.example.rickandmorty.main.presentation

import android.app.Application
import com.example.rickandmorty.main.di.ApplicationComponent
import com.example.rickandmorty.main.di.DaggerApplicationComponent

class RickAndMortyApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}