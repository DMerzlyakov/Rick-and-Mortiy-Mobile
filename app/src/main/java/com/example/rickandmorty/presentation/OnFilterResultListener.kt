package com.example.rickandmorty.presentation

import com.example.rickandmorty.domain.CharactersFilter

interface OnFilterResultListener {
    fun confirmFilter(item: CharactersFilter? = null)
}