package com.example.rickandmorty.character.presentation.list.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterItem
import com.example.rickandmorty.databinding.ItemCharacterBinding

class CharactersRecyclerViewAdapter(private val onClickRecyclerViewInterface: OnClickRecyclerViewInterface<CharacterItem>) :
    PagingDataAdapter<CharacterItem, CharacterViewHolder>(CharactersDiffUtil()) {
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
        holder.onBind(getItem(position) ?: return)
    }
}