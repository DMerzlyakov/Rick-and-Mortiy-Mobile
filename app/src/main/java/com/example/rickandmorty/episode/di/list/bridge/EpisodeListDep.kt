package com.example.rickandmorty.episode.di.list.bridge

import android.content.Context
import retrofit2.Retrofit

interface EpisodeListDep {

    val context: Context

    val retrofit: Retrofit
}