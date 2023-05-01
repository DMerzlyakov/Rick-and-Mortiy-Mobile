package com.example.rickandmorty.character.presentation.list.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.character.presentation.list.model.CharacterUiModel

class CharactersDiffUtil : DiffUtil.ItemCallback<CharacterUiModel>() {
    override fun areItemsTheSame(oldItem: CharacterUiModel, newItem: CharacterUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterUiModel, newItem: CharacterUiModel): Boolean {
        return oldItem == newItem
    }
}