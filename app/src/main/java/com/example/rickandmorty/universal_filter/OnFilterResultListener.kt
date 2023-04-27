package com.example.rickandmorty.universal_filter

import com.example.rickandmorty.character.domain.list.model.CharactersFilter

interface OnFilterResultListener {
    fun confirmFilter(item: CharactersFilter? = null)
}