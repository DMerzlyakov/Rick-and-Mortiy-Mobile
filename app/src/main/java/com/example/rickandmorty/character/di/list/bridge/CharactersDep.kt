package com.example.rickandmorty.character.di.list.bridge

import android.content.Context
import retrofit2.Retrofit

interface CharactersDep {

    val context: Context

    val retrofit: Retrofit
}