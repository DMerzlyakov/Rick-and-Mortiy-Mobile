package com.example.rickandmorty.presentation.characters

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.domain.Character

class CharactersDiffUtil: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        TODO("Not yet implemented")
    }
}