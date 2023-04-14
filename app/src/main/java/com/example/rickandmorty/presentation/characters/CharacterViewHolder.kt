package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.domain.Character
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemCharacterBinding

class CharacterViewHolder(
    private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            //TODO
        }
    }

    fun onBind(item: Character) {
        TODO("onBind create")
    }


}