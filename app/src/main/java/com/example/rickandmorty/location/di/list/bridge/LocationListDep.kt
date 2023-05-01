package com.example.rickandmorty.location.di.list.bridge

import android.content.Context
import retrofit2.Retrofit

interface LocationListDep {

    val context: Context

    val retrofit: Retrofit
}