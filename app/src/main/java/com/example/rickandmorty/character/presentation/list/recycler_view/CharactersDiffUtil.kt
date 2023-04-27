package com.example.rickandmorty.character.presentation.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterItem

class CharactersDiffUtil : DiffUtil.ItemCallback<CharacterItem>() {
    override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
        return oldItem == newItem
    }
}