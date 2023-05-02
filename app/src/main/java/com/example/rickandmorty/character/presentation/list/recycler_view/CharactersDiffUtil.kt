package com.example.rickandmorty.character.presentation.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.character.presentation.list.model.CharacterUi

class CharactersDiffUtil : DiffUtil.ItemCallback<CharacterUi>() {
    override fun areItemsTheSame(oldItem: CharacterUi, newItem: CharacterUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterUi, newItem: CharacterUi): Boolean {
        return oldItem == newItem
    }
}