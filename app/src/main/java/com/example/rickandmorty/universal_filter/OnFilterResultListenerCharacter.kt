package com.example.rickandmorty.universal_filter

import com.example.rickandmorty.character.domain.list.model.CharacterFilter

interface OnFilterResultListenerCharacter {
    fun confirmFilter(item: CharacterFilter? = null)

}