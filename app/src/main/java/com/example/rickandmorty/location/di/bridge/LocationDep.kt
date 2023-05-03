package com.example.rickandmorty.location.di.bridge

import android.content.Context
import retrofit2.Retrofit

interface LocationDep {

    val context: Context

    val retrofit: Retrofit
}