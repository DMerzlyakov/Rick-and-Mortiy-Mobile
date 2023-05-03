package com.example.rickandmorty.episode.di.bridge

import android.content.Context
import retrofit2.Retrofit

interface EpisodeDep {

    val context: Context

    val retrofit: Retrofit
}