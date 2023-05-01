package com.example.rickandmorty.universal_filter

import com.example.rickandmorty.character.domain.list.model.CharacterFilter

interface OnFilterResultListener {
    fun confirmFilter(item: CharacterFilter? = null)
}