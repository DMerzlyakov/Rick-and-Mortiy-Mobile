package com.example.rickandmorty.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.presentation.OnClickRecyclerViewInterface

class CharactersRecyclerViewAdapter(private val onClickRecyclerViewInterface: OnClickRecyclerViewInterface<Character>) :
    ListAdapter<Character, CharacterViewHolder>(CharactersDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickRecyclerViewInterface

        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}